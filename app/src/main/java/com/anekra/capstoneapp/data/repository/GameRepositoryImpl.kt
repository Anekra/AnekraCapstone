package com.anekra.capstoneapp.data.repository

import androidx.paging.*
import com.anekra.capstoneapp.data.local.db.GameListDatabase
import com.anekra.capstoneapp.data.network.RawgApi
import com.anekra.capstoneapp.data.paging.GameRemoteMediator
import com.anekra.capstoneapp.data.paging.SearchRemoteMediator
import com.anekra.capstoneapp.domain.model.game.GameDetails
import com.anekra.capstoneapp.domain.model.game.GameList
import com.anekra.capstoneapp.domain.model.game.GameWithScreenShotsDomain
import com.anekra.capstoneapp.domain.model.game.ScreenShotsItem
import com.anekra.capstoneapp.domain.repository.GameRepository
import com.anekra.capstoneapp.util.*
import com.anekra.capstoneapp.util.Constants.DEFAULT_PAGE_SIZE
import kotlinx.coroutines.flow.*
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@OptIn(ExperimentalPagingApi::class)
@Singleton
class GameRepositoryImpl @Inject constructor(
    private val api: RawgApi,
    private val db: GameListDatabase,
) : GameRepository {
    override fun getGameList(
        fetchFromRemote: Boolean,
        queries: HashMap<String, Any>?,
    ): Flow<PagingData<GameList>> {
        return Pager(
            config = PagingConfig(pageSize = DEFAULT_PAGE_SIZE),
            remoteMediator = GameRemoteMediator(
                api = api,
                db = db,
                fetchFromRemote = fetchFromRemote
            )
        ) {
            db.gameDao().getGameListAsPagingSource()
        }.flow.map { pagingData ->
            pagingData.map { gameListEntity ->
                gameListEntity.toGameList()
            }
        }
    }
    
    override fun searchGameList(
        query: String,
    ): Flow<PagingData<GameList>> {
        return Pager(
            config = PagingConfig(pageSize = DEFAULT_PAGE_SIZE),
            remoteMediator = SearchRemoteMediator(
                api = api,
                db = db,
                query = query
            )
        ) {
            db.gameDao().searchGameList(query = query)
        }.flow.map { pagingData ->
            pagingData.map { gameListEntity ->
                gameListEntity.toGameList()
            }
        }
    }
    
    override suspend fun getGameListCount(): Int {
        return db.gameDao().getGameListCount()
    }
    
    override suspend fun getLocalGameDetails(id: String): GameDetails? {
        return db.gameDao().getGameDetails(id = id)?.toGameDetails()
    }
    
    override fun getLocalGameWithScreenShots(gameDetailsId: String): Flow<GameWithScreenShotsDomain> {
        return flow {
            db.gameDao().getGameWithScreenShots(gameDetailsId = gameDetailsId)?.collect {
                emit(
                    value = GameWithScreenShotsDomain(
                        gameDetails = it.gameDetails.toGameDetails(),
                        screenShots = it.screenShots.toListOfScreenShotsItemDomain()
                    )
                )
            } ?: run {
                emit(
                    value = GameWithScreenShotsDomain(
                        gameDetails = null,
                        screenShots = null
                    )
                )
            }
        }
    }
    
    override suspend fun addGameToFavorite(game: GameDetails) {
        db.gameDao().addGameToFavorite(data = game.toGameDetailsEntity())
    }
    
    override suspend fun addScreenShotsToFavorite(screenShots: List<ScreenShotsItem>, id: String) {
        db.gameDao().addScreenShotsToFavorite(
            data = screenShots.toListOfScreenShotsItemEntity(gameDetailsId = id)
        )
    }
    
    override suspend fun deleteGameWithScreenShots(gameDetails: GameDetails) {
        db.gameDao().deleteGameWithScreenShots(gameDetails = gameDetails.toGameDetailsEntity())
    }
    
    override suspend fun deleteAllGameListWithScreenShots() {
        db.gameDao().deleteAllGameListWithScreenShots()
    }
    
    override fun getLocalGameListWithScreenShots(): Flow<List<GameWithScreenShotsDomain>> {
        return flow {
            db.gameDao().getGameListWithScreenShots()?.let { flowOfGameWithScreenShotsList ->
                flowOfGameWithScreenShotsList.collect { gameWithScreenShotsList ->
                gameWithScreenShotsList.logAsString("GameRepoImpl")
                    val lastIndex = gameWithScreenShotsList.size - 1
                    val gameDetails = (0..lastIndex).map {
                        gameWithScreenShotsList[it].gameDetails
                    }
                    val screenShots = (0..lastIndex).map {
                        gameWithScreenShotsList[it].screenShots
                    }
                    val results = gameDetails.zip(screenShots) { gameDetailsEntity, screenShotsEntity ->
                        GameWithScreenShotsDomain(
                            gameDetails = gameDetailsEntity.toGameDetails(),
                            screenShots = screenShotsEntity.toListOfScreenShotsItemDomain()
                        )
                    }
                    
                    emit(value = results)
                }
            }
        }
    }
    
    override fun getGameDetails(id: String): Flow<Resource<GameDetails>> {
        return flow {
            try {
                emit(Resource.Loading(true))
                val response = api.getGameListDetails(id = id).body()?.toGameDetails()
                id.logAsString("GameRepoImpl1")
                api.getGameListDetails(id = id).logAsString("GameRepoImpl2")
                emit(Resource.Success(data = response))
            } catch (e: IOException) {
                e.printStackTrace()
                emit(Resource.Error(e.message.toString()))
            } catch (e: HttpException) {
                e.printStackTrace()
                emit(Resource.Error(e.message.toString()))
            }
        }
    }
    
    override fun getGameScreenShots(id: String): Flow<Resource<List<ScreenShotsItem>>> {
        return flow {
            try {
                emit(Resource.Loading(true))
                val response =
                    api.getGameScreenShots(id = id).body()?.results?.toListOfScreenShotsItem()
                emit(Resource.Success(data = response))
            } catch (e: IOException) {
                e.printStackTrace()
                emit(Resource.Error(e.message.toString()))
            } catch (e: HttpException) {
                e.printStackTrace()
                emit(Resource.Error(e.message.toString()))
            }
        }
    }
    
}