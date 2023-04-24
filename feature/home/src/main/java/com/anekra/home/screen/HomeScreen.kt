package com.anekra.home.screen

import android.content.Context
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.paging.compose.LazyPagingItems
import com.anekra.domain.model.game.GameList
import com.anekra.home.component.*
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshState

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
    navigateToDetails: (String) -> Unit,
    lazyPagingItems: LazyPagingItems<GameList>,
    viewModel: HomeViewModel,
    pagerState: PagerState,
    paddingValues: PaddingValues,
    swipeRefreshState: SwipeRefreshState,
    context: Context
) {
    SwipeRefresh(
        state = swipeRefreshState,
        onRefresh = {
            viewModel.onEvent(HomeScreenEvent.Refresh)
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(
                    top = paddingValues.calculateTopPadding(),
                    bottom = paddingValues.calculateBottomPadding()
                )
        ) {
            BannerContent(
                viewModel = viewModel,
                pagerState = pagerState,
                pageCount = 8,
                lazyPagingItems = lazyPagingItems
            )
            CategoriesContent(context = context)
            FeaturedGamesContent(
                navigateToDetails = navigateToDetails,
                viewModel = viewModel,
                lazyPagingItems = lazyPagingItems,
                context = context
            )
            RawgContent()
            PlatformContent(context = context)
            GenreContent(context = context)
            DisclaimerContent()
        }
    }
}
