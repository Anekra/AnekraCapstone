package com.anekra.search.screen

import androidx.paging.PagingData
import com.anekra.domain.model.game.GameList
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

data class SearchScreenState(
    val searchedGames: Flow<PagingData<GameList>> = flowOf(PagingData.empty()),
    val isRefreshing: Boolean = false,
    val searchQuery: String = "",
)
