package com.anekra.capstoneapp.presentation.screen.home

import androidx.paging.PagingData
import com.anekra.capstoneapp.domain.model.game.GameList
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

data class HomeScreenState(
    val games: Flow<PagingData<GameList>> = flowOf(PagingData.empty()),
    val isLoading: Boolean = false,
    val isRefreshing: Boolean = false,
    val error: String = ""
)
