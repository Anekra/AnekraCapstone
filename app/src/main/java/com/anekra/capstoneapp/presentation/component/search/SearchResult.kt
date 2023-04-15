package com.anekra.capstoneapp.presentation.component.search

import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.anekra.capstoneapp.domain.model.GameList
import com.anekra.capstoneapp.presentation.component.home.EsrbRatingHolder
import com.anekra.capstoneapp.presentation.component.home.FeaturedFooter
import com.anekra.capstoneapp.presentation.component.home.RatingHolder
import com.anekra.capstoneapp.presentation.screen.search.SearchViewModel
import com.anekra.capstoneapp.util.ImageHolder
import com.anekra.capstoneapp.util.getTabList
import com.anekra.capstoneapp.util.items
import com.anekra.capstoneapp.util.logAsString
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SearchResultContent(pagerState: PagerState, viewModel: SearchViewModel) {
    Row(modifier = Modifier.padding(top = 16.dp)) {
        SearchResultTabs(pagerState = pagerState, viewModel = viewModel)
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SearchResultTabs(pagerState: PagerState, viewModel: SearchViewModel) {
    val coroutineScope = rememberCoroutineScope()
    val pagingData = remember { mutableStateOf(flowOf(PagingData.empty<GameList>())) }
    val pagingItems = remember { mutableStateOf(pagingData.value) }
    
    Column {
        ScrollableTabRow(
            selectedTabIndex = pagerState.currentPage,
            indicator = {
                CustomIndicator(tabPositions = it, pagerState = pagerState)
            },
            edgePadding = 16.dp
        ) {
            getTabList().forEachIndexed { index, title ->
                Tab(
                    modifier = Modifier
                        .zIndex(1f)
                        .clip(RoundedCornerShape(topStart = 5.dp, topEnd = 5.dp))
                        .background(
                            color =
                            if (index == pagerState.currentPage) MaterialTheme.colorScheme.primaryContainer
                            else MaterialTheme.colorScheme.surface
                        ),
                    text = {
                        Text(
                            modifier = Modifier.zIndex(3f),
                            text = title,
                            color =
                            if (index == pagerState.currentPage) MaterialTheme.colorScheme.onPrimaryContainer
                            else Color.White
                        )
                    },
                    selected = pagerState.currentPage == index,
                    onClick = {
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(index)
                        }
                    }
                )
            }
        }
        
        HorizontalPager(
            state = pagerState,
            pageCount = getTabList().size
        ) {
            viewModel.searchState.apply {
                pagingData.value = when (it) {
                    0 -> searchedGames
                    1 -> searchedCreators
                    2 -> searchedDevelopers
                    3 -> searchedPlatforms
                    4 -> searchedGenres
                    5 -> searchedPublishers
                    6 -> searchedTags
                    7 -> searchedStores
                    else -> pagingData.value
                }
                pagingItems.value = pagingData.value
                pagingData.logAsString("SearchResult")
                pagingItems.logAsString("SearchResult")
            }
            val lazyPagingItems = pagingItems.value.collectAsLazyPagingItems()
    
            StaggeredGridSearchResult(lazyPagingItems = lazyPagingItems)
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun StaggeredGridSearchResult(
    lazyPagingItems: LazyPagingItems<GameList>,
) {
    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Fixed(2),
        contentPadding = PaddingValues(all = 16.dp),
        verticalItemSpacing = 8.dp,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(
            items = lazyPagingItems,
            key = {
                it.remoteId
            }
        ) { game ->
            Column(
                modifier = Modifier
                    .clip(RoundedCornerShape(size = 10.dp))
                    .background(color = MaterialTheme.colorScheme.surface)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(color = MaterialTheme.colorScheme.primaryContainer),
                    contentAlignment = Alignment.Center,
                ) {
                    game.backgroundImage?.let { image ->
                        ImageHolder(
                            imageUrl = image,
                            showGradient = false
                        )
                    }
                    RatingHolder(
                        modifier = Modifier.align(Alignment.TopEnd),
                        data = game
                    )
                    EsrbRatingHolder(
                        modifier = Modifier.align(Alignment.BottomEnd),
                        data = game
                    )
                }
                FeaturedFooter(data = game)
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CustomIndicator(
    tabPositions: List<TabPosition>,
    pagerState: PagerState,
) {
    val transition = updateTransition(pagerState.currentPage, label = "Indicator")
    val indicatorStart by transition.animateDp(
        transitionSpec = {
            if (initialState < targetState) {
                spring(dampingRatio = 0.5f, stiffness = 500f)
            } else {
                spring(dampingRatio = 0.5f, stiffness = 1000f)
            }
        }, label = ""
    ) {
        tabPositions[it].left
    }
    
    val indicatorEnd by transition.animateDp(
        transitionSpec = {
            if (initialState < targetState) {
                spring(dampingRatio = 0.5f, stiffness = 1000f)
            } else {
                spring(dampingRatio = 0.5f, stiffness = 500f)
            }
        }, label = ""
    ) {
        tabPositions[it].right
    }
    
    Box(
        modifier = Modifier
            .offset(x = indicatorStart)
            .wrapContentSize(align = Alignment.BottomStart)
            .width(indicatorEnd - indicatorStart)
            .height(2.dp)
            .background(color = MaterialTheme.colorScheme.onPrimaryContainer)
            .zIndex(2f)
    )
}