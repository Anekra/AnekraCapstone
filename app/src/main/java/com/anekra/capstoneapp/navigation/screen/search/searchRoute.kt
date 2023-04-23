package com.anekra.capstoneapp.navigation.screen.search

import android.app.Activity
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.paging.compose.collectAsLazyPagingItems
import com.anekra.capstoneapp.navigation.screen.Screens
import com.anekra.capstoneapp.presentation.screen.search.SearchScreen
import com.anekra.capstoneapp.presentation.screen.search.SearchViewModel
import com.anekra.capstoneapp.util.HandleBackPress
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

fun NavGraphBuilder.searchRoute(
    navHostController: NavHostController,
    paddingValues: PaddingValues,
) {
    composable(route = Screens.Search.route) {
        val viewModel: SearchViewModel = hiltViewModel()
        val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = viewModel.searchState.isRefreshing)
        val lazyPagingItems = viewModel.searchState.searchedGames.collectAsLazyPagingItems()
        val context = LocalContext.current
    
        SearchScreen(
            navigateToDetails = {
                navHostController.navigate(Screens.Details.passGameId(it))
            },
            viewModel = viewModel,
            swipeRefreshState = swipeRefreshState,
            paddingValues = paddingValues,
            lazyPagingItems = lazyPagingItems
        )
    
        HandleBackPress {
            (context as Activity).finish()
        }
    }
}