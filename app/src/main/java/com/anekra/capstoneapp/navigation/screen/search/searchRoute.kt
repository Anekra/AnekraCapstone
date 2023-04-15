package com.anekra.capstoneapp.navigation.screen.search

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.pager.rememberPagerState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.paging.compose.collectAsLazyPagingItems
import com.anekra.capstoneapp.navigation.screen.Screens
import com.anekra.capstoneapp.presentation.screen.search.SearchScreen
import com.anekra.capstoneapp.presentation.screen.search.SearchViewModel
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@OptIn(ExperimentalFoundationApi::class)
fun NavGraphBuilder.searchRoute(
    navHostController: NavHostController,
    paddingValues: PaddingValues,
) {
    composable(route = Screens.Search.route) {
        val viewModel: SearchViewModel = hiltViewModel()
        val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = viewModel.searchState.isRefreshing)
    
        SearchScreen(
            navHostController = navHostController,
            viewModel = viewModel,
            swipeRefreshState = swipeRefreshState,
            pagerState = rememberPagerState(),
            paddingValues = paddingValues
        )
    }
}