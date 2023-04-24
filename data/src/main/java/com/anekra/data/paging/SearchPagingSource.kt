package com.anekra.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.anekra.data.mapper.toGameList
import com.anekra.data.network.RawgApi
import com.anekra.domain.model.game.GameList
import com.anekra.util.Constants.DEFAULT_PAGE_SIZE
import com.anekra.util.applyQueries


class SearchPagingSource(
    private val api: RawgApi,
    private val query: String
) : PagingSource<Int, GameList>() {
    
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, GameList> {
        val currentPage = params.key ?: 1
        return try {
            val response = api.getGameList(applyQueries(page = currentPage, pageSize = DEFAULT_PAGE_SIZE, search = query))
            val results = response.body()?.results
            val endOfPaginationReached = results.isNullOrEmpty()
            if (results?.isNotEmpty() == true) {
                LoadResult.Page(
                    data = results.map { it.toGameList() },
                    prevKey = if (currentPage == 1) null else currentPage - 1,
                    nextKey = if (endOfPaginationReached) null else currentPage + 1
                )
            } else {
                LoadResult.Page(
                    data = emptyList(),
                    prevKey = null,
                    nextKey = null
                )
            }
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
    
    override fun getRefreshKey(state: PagingState<Int, GameList>): Int? {
        return state.anchorPosition
    }
}