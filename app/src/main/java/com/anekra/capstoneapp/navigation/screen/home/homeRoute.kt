package com.anekra.capstoneapp.navigation.screen.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.paging.compose.collectAsLazyPagingItems
import com.anekra.capstoneapp.navigation.screen.Screens
import com.anekra.capstoneapp.presentation.screen.home.HomeScreen
import com.anekra.capstoneapp.presentation.screen.home.HomeViewModel
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@OptIn(ExperimentalFoundationApi::class)
fun NavGraphBuilder.homeRoute(
    navHostController: NavHostController,
    paddingValues: PaddingValues,
    onScreenLoaded: () -> Unit,
) {
    composable(route = Screens.Home.route) {
        val viewModel: HomeViewModel = hiltViewModel()
        val lazyPagingItems = viewModel.homeState.games.collectAsLazyPagingItems()
        val items = remember { mutableStateOf(lazyPagingItems) }
        val pagerState = rememberPagerState()
        val swipeRefreshState =
            rememberSwipeRefreshState(isRefreshing = viewModel.homeState.isRefreshing)
        val context = LocalContext.current
        
        LaunchedEffect(key1 = lazyPagingItems.loadState.append.endOfPaginationReached) {
            onScreenLoaded()
            items.value = lazyPagingItems
        }
        
        HomeScreen(
            navigateToDetails = {
                navHostController.navigate(Screens.Details.passGameId(gameId = it))
            },
            viewModel = viewModel,
            lazyPagingItems = items.value,
            pagerState = pagerState,
            paddingValues = paddingValues,
            swipeRefreshState = swipeRefreshState,
            context = context
        )
        
    }
}

