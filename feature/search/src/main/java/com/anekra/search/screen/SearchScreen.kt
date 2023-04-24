package com.anekra.search.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.anekra.domain.model.game.GameList
import com.anekra.search.R
import com.anekra.search.component.SearchBarContent
import com.anekra.search.component.SearchResultContent
import com.anekra.util.ErrorText
import com.anekra.util.LoadingBar
import com.anekra.util.logAsString
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshState

@Composable
fun SearchScreen(
    navigateToDetails: (String) -> Unit,
    viewModel: SearchViewModel,
    swipeRefreshState: SwipeRefreshState,
    paddingValues: PaddingValues,
    lazyPagingItems: LazyPagingItems<GameList>,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = paddingValues.calculateTopPadding())
    ) {
        val isLoading = lazyPagingItems.loadState.refresh
        SearchBarContent(
            viewModel = viewModel
        )
        SwipeRefresh(
            modifier = Modifier.padding(bottom = paddingValues.calculateBottomPadding()),
            state = swipeRefreshState,
            onRefresh = { viewModel.onEvent(SearchScreenEvent.Refresh) }
        ) {
            when (isLoading) {
                is LoadState.NotLoading -> {
                    if (lazyPagingItems.itemCount > 0) {
                        SearchResultContent(
                            navigateToDetails = navigateToDetails,
                            lazyPagingItems = lazyPagingItems
                        )
                    } else {
                        ErrorText(error = stringResource(R.string.no_data_found))
                    }
                }
                is LoadState.Loading ->
                    LoadingBar()
                is LoadState.Error ->
                    ErrorText(error = isLoading.error.message.toString())
            }
            
            lazyPagingItems.loadState.refresh.logAsString("searchScreen")
        }
    }
}