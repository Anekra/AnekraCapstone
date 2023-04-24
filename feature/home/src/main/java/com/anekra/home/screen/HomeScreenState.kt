package com.anekra.home.screen

import androidx.paging.PagingData
import com.anekra.domain.model.game.GameList
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

data class HomeScreenState(
    val games: Flow<PagingData<GameList>> = flowOf(PagingData.empty()),
    val isLoading: Boolean = false,
    val isRefreshing: Boolean = false,
    val error: String = ""
)
