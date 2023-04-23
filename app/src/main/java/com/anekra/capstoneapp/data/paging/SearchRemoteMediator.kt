package com.anekra.capstoneapp.data.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.anekra.capstoneapp.data.local.db.GameListDatabase
import com.anekra.capstoneapp.data.local.entity.game.GameListEntity
import com.anekra.capstoneapp.data.local.entity.paging.RemoteKeysEntity
import com.anekra.capstoneapp.util.toGameList
import com.anekra.capstoneapp.util.toGameListEntity
import com.anekra.capstoneapp.data.network.RawgApi
import com.anekra.capstoneapp.util.Constants.DEFAULT_PAGE_SIZE
import com.anekra.capstoneapp.util.applyQueries
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class SearchRemoteMediator @Inject constructor(
    private val api: RawgApi,
    private val db: GameListDatabase,
    private val query: String
) : RemoteMediator<Int, GameListEntity>() {
    
    private val remoteKeysDao = db.remoteKeysDao()
    
    override suspend fun initialize(): InitializeAction {
        return InitializeAction.SKIP_INITIAL_REFRESH
    }
    
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, GameListEntity>,
    ): MediatorResult {
        return try {
            val currentPage = when (loadType) {
                LoadType.REFRESH -> {
                    val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                    remoteKeys?.nextPage?.minus(1) ?: 1
                }
                LoadType.PREPEND -> {
                    val remoteKeys = getRemoteKeyForFirstItem(state)
                    val prevPage = remoteKeys?.prevPage
                        ?: return MediatorResult.Success(
                            endOfPaginationReached = remoteKeys != null
                        )
                    prevPage
                }
                LoadType.APPEND -> {
                    val remoteKeys = getRemoteKeyForLastItem(state)
                    val nextPage = remoteKeys?.nextPage
                        ?: return MediatorResult.Success(
                            endOfPaginationReached = remoteKeys != null
                        )
                    nextPage
                }
            }
            val response = api.getGameList(
                applyQueries(
                    page = currentPage,
                    pageSize = DEFAULT_PAGE_SIZE,
                    search = query
                )
            ).body()?.results?.map { gameListResponse ->
                gameListResponse.toGameList()
            }
            
            val endOfPaginationReached = response.isNullOrEmpty()
            
            val prevPage = if (currentPage == 1) null else currentPage - 1
            val nextPage = if (endOfPaginationReached) null else currentPage + 1
            
            db.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    db.gameDao().deleteAllGameList()
                    remoteKeysDao.deleteAllRemoteKeys()
                }
                response?.let {
                    val keys = response.map { list ->
                        RemoteKeysEntity(
                            id = list.remoteId,
                            prevPage = prevPage,
                            nextPage = nextPage
                        )
                    }
                    val data = it
                    remoteKeysDao.addAllRemoteKeys(remoteKeyEntities = keys)
                    db.gameDao().insertGameList(list = data.toGameListEntity())
                }
            }
            MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
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
            state.closestPageToPosition(position)?.data?.get(position)?.remoteId?.let { id ->
                remoteKeysDao.getRemoteKeys(id = id)
            }
        }
    }
    
    private suspend fun getRemoteKeyForFirstItem(
        state: PagingState<Int, GameListEntity>,
    ): RemoteKeysEntity? {
        return state.pages.firstOrNull {
            it.data.isNotEmpty()
        }?.data?.firstOrNull()?.let { gameListEntity ->
            remoteKeysDao.getRemoteKeys(id = gameListEntity.remoteId)
        }
    }
    
    private suspend fun getRemoteKeyForLastItem(
        state: PagingState<Int, GameListEntity>,
    ): RemoteKeysEntity? {
        return state.pages.lastOrNull {
            it.data.isNotEmpty()
        }?.data?.lastOrNull()?.let { gameListEntity ->
            remoteKeysDao.getRemoteKeys(id = gameListEntity.remoteId)
        }
    }
}