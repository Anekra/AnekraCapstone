package com.anekra.home.component

import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import com.anekra.domain.model.game.GameList
import com.anekra.home.screen.HomeViewModel
import com.anekra.ui.component.HorizontalPagerIndicator
import com.anekra.util.ImageHolder
import com.anekra.util.LoadingBar
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun BannerContent(
    viewModel: HomeViewModel,
    pagerState: PagerState,
    pageCount: Int,
    lazyPagingItems: LazyPagingItems<GameList>,
) {
    Row(modifier = Modifier.height(220.dp)) {
        Box {
            if (viewModel.homeState.isLoading)
                LoadingBar()
            else {
                if (lazyPagingItems.itemSnapshotList.items.isNotEmpty()) {
                    BannerImage(
                        pagerState = pagerState,
                        pageCount = pageCount,
                        gameList = lazyPagingItems.itemSnapshotList.items
                    )
                    HorizontalPagerIndicator(
                        pagerState = pagerState,
                        pageCount = 8,
                        modifier = Modifier.align(Alignment.BottomCenter)
                    )
                }
            }
        }
        LaunchedEffect(key1 = pagerState.settledPage) {
            launch {
                delay(3000)
                with(pagerState) {
                    animateScrollToPage(
                        page = if (currentPage < pageCount - 1) currentPage + 1 else 0,
                        animationSpec = tween(
                            durationMillis = 500
                        )
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun BannerImage(
    pagerState: PagerState,
    pageCount: Int,
    gameList: List<GameList>,
) {
    HorizontalPager(
        state = pagerState,
        pageCount = pageCount
    ) {
        gameList[it].backgroundImage?.let { imageUrl ->
            ImageHolder(imageUrl = imageUrl, showGradient = true)
        }
    }
}