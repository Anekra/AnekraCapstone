package com.anekra.capstoneapp.presentation.screen.search

import androidx.paging.PagingData
import com.anekra.capstoneapp.domain.model.game.GameList
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

data class SearchScreenState(
    val searchedGames: Flow<PagingData<GameList>> = flowOf(PagingData.empty()),
    val isLoading: Boolean = false,
    val isRefreshing: Boolean = false,
    val searchQuery: String = "",
    val error: String = ""
)
