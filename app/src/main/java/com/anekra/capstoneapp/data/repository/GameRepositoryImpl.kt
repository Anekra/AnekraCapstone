package com.anekra.capstoneapp.data.repository

import androidx.paging.*
import com.anekra.capstoneapp.data.local.db.GameListDatabase
import com.anekra.capstoneapp.data.mapper.toGameList
import com.anekra.capstoneapp.data.network.RawgApi
import com.anekra.capstoneapp.data.paging.GameRemoteMediator
import com.anekra.capstoneapp.data.paging.SearchRemoteMediator
import com.anekra.capstoneapp.domain.model.GameList
import com.anekra.capstoneapp.domain.repository.GameRepository
import com.anekra.capstoneapp.util.Constants.DEFAULT_PAGE_SIZE
import kotlinx.coroutines.flow.*
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
}