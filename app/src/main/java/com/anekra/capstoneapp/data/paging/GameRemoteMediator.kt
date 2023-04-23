package com.anekra.capstoneapp.data.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.anekra.capstoneapp.data.local.db.GameListDatabase
import com.anekra.capstoneapp.data.local.entity.game.GameListEntity
import com.anekra.capstoneapp.data.local.entity.paging.RemoteKeysEntity
import com.anekra.capstoneapp.data.network.RawgApi
import com.anekra.capstoneapp.util.Constants.DEFAULT_PAGE_SIZE
import com.anekra.capstoneapp.util.applyQueries
import com.anekra.capstoneapp.util.logAsString
import com.anekra.capstoneapp.util.toGameList
import com.anekra.capstoneapp.util.toGameListEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class GameRemoteMediator @Inject constructor(
    private val api: RawgApi,
    private val db: GameListDatabase,
    private val fetchFromRemote: Boolean,
) : RemoteMediator<Int, GameListEntity>() {
    
    private val remoteKeysDao = db.remoteKeysDao()
    
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, GameListEntity>,
    ): MediatorResult {
        return try {
            val localDataCount = withContext(Dispatchers.IO) {
                db.gameDao().getGameListCount()
            }
            if (localDataCount <= 11 || fetchFromRemote) {
                val currentPage = when (loadType) {
                    LoadType.REFRESH -> {
                        val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                        remoteKeys.logAsString("GRM refresh")
                        remoteKeys?.nextPage?.minus(1) ?: 1
                    }
                    LoadType.PREPEND -> {
                        val remoteKeys = getRemoteKeyForFirstItem(state)
                        val prevPage = remoteKeys?.prevPage
                            ?: return MediatorResult.Success(
                                endOfPaginationReached = remoteKeys != null
                            )
                        prevPage.logAsString("GRM prepend")
                        prevPage
                    }
                    LoadType.APPEND -> {
                        val remoteKeys = getRemoteKeyForLastItem(state)
                        val nextPage = remoteKeys?.nextPage
                            ?: return MediatorResult.Success(
                                endOfPaginationReached = remoteKeys != null
                            )
                        nextPage.logAsString("GRM append")
                        nextPage
                    }
                }
                currentPage.logAsString("GRM currentPage")
                val response =
                    api.getGameList(applyQueries(page = currentPage, pageSize = DEFAULT_PAGE_SIZE))
                        .body()?.results?.map { gameListResponse ->
                            gameListResponse.toGameList()
                        }
                response.logAsString("GRM response 2")
                val endOfPaginationReached = response.isNullOrEmpty()
                val prevPage = if (currentPage == 1) null else currentPage - 1
                val nextPage = if (endOfPaginationReached) null else currentPage + 1
                
                db.withTransaction {
                    if (loadType == LoadType.REFRESH) {
                        db.gameDao().deleteAllGameList()
                        remoteKeysDao.deleteAllRemoteKeys()
                    }
                    response?.let {
                        val keys = it.map { list ->
                            RemoteKeysEntity(
                                id = list.remoteId,
                                prevPage = prevPage,
                                nextPage = nextPage
                            )
                        }
                        remoteKeysDao.addAllRemoteKeys(remoteKeyEntities = keys)
                        db.gameDao().insertGameList(list = response.toGameListEntity())
                    }
                }
                MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
            } else {
                MediatorResult.Success(endOfPaginationReached = true)
            }
        } catch (e: IOException) {
            return MediatorResult.Error(e)
        } catch (e: HttpException) {
            return MediatorResult.Error(e)
        }
    }
    
    private suspend fun getRemoteKeyClosestToCurrentPosition(
        state: PagingState<Int, GameListEntity>,
    ): RemoteKeysEntity? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.remoteId?.let { id ->
                id.logAsString("GRM getRemoteKeyClosestToCurrentPosition id")
                remoteKeysDao.getRemoteKeys(id = id)
            }
        }
    }
    
    private suspend fun getRemoteKeyForFirstItem(
        state: PagingState<Int, GameListEntity>,
    ): RemoteKeysEntity? {
        return state.pages.firstOrNull {
            it.data.isNotEmpty()
        }?.data?.firstOrNull()?.let { gameList ->
            remoteKeysDao.getRemoteKeys(id = gameList.remoteId)
        }
    }
    
    private suspend fun getRemoteKeyForLastItem(
        state: PagingState<Int, GameListEntity>,
    ): RemoteKeysEntity? {
        return state.pages.lastOrNull {
            it.data.isNotEmpty()
        }?.data?.lastOrNull()?.let { gameList ->
            remoteKeysDao.getRemoteKeys(id = gameList.remoteId)
        }
    }
}