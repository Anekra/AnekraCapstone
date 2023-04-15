package com.anekra.capstoneapp.presentation.screen.search

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.PagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.anekra.capstoneapp.presentation.component.search.SearchBarContent
import com.anekra.capstoneapp.presentation.component.search.SearchResultContent
import com.anekra.capstoneapp.util.LoadingBar
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshState

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SearchScreen(
    navHostController: NavHostController,
    viewModel: SearchViewModel,
    swipeRefreshState: SwipeRefreshState,
    pagerState: PagerState,
    paddingValues: PaddingValues,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = paddingValues.calculateTopPadding())
    ) {
        SearchBarContent(searchViewModel = viewModel)
        SwipeRefresh(
            modifier = Modifier.padding(bottom = paddingValues.calculateBottomPadding()),
            state = swipeRefreshState,
            onRefresh = { viewModel.onEvent(SearchScreenEvent.Refresh) }
        ) {
            if (viewModel.searchState.isLoading)
                LoadingBar()
            else
                SearchResultContent(pagerState = pagerState, viewModel = viewModel)
        }
    }
}