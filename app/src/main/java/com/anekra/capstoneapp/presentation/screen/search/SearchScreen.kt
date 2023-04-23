package com.anekra.capstoneapp.presentation.screen.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.compose.LazyPagingItems
import com.anekra.capstoneapp.domain.model.game.GameList
import com.anekra.capstoneapp.presentation.component.search.SearchBarContent
import com.anekra.capstoneapp.presentation.component.search.SearchResultContent
import com.anekra.capstoneapp.util.LoadingBar
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshState

@Composable
fun SearchScreen(
    navigateToDetails: (String) -> Unit,
    viewModel: SearchViewModel,
    swipeRefreshState: SwipeRefreshState,
    paddingValues: PaddingValues,
    lazyPagingItems: LazyPagingItems<GameList>
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = paddingValues.calculateTopPadding())
    ) {
        SearchBarContent(
            viewModel = viewModel,
            lazyPagingItems = lazyPagingItems
        )
        SwipeRefresh(
            modifier = Modifier.padding(bottom = paddingValues.calculateBottomPadding()),
            state = swipeRefreshState,
            onRefresh = { viewModel.onEvent(SearchScreenEvent.Refresh) }
        ) {
            if (viewModel.searchState.isLoading)
                LoadingBar()
            else
                SearchResultContent(
                    navigateToDetails = navigateToDetails,
                    lazyPagingItems = lazyPagingItems
                )
        }
    }
}