package com.anekra.capstoneapp.presentation.component.home

import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import com.anekra.capstoneapp.domain.model.game.GameList
import com.anekra.capstoneapp.presentation.screen.home.HomeViewModel
import com.anekra.capstoneapp.util.ImageHolder
import com.anekra.capstoneapp.util.LoadingBar
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

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HorizontalPagerIndicator(
    pagerState: PagerState,
    pageCount: Int,
    modifier: Modifier,
    spacing: Dp = 4.dp,
    activeColor: Color = MaterialTheme.colorScheme.onSurface,
    inactiveColor: Color = activeColor.copy(alpha = 0.5f),
    indicatorShape: Shape = CircleShape,
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        repeat(pageCount) { iteration ->
            val color = if (pagerState.currentPage == iteration) activeColor else inactiveColor
            val size = if (pagerState.currentPage == iteration) 12.dp else 8.dp
            Box(
                modifier = modifier
                    .padding(spacing)
                    .clip(indicatorShape)
                    .background(color)
                    .size(size)
            )
        }
    }
}