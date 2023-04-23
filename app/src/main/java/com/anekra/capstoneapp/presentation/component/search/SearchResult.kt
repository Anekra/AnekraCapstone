package com.anekra.capstoneapp.presentation.component.search

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import com.anekra.capstoneapp.domain.model.game.GameList
import com.anekra.capstoneapp.presentation.component.home.EsrbRatingHolder
import com.anekra.capstoneapp.presentation.component.home.FeaturedFooter
import com.anekra.capstoneapp.presentation.component.home.RatingHolder
import com.anekra.capstoneapp.util.ImageHolder
import com.anekra.capstoneapp.util.items

@Composable
fun SearchResultContent(
    navigateToDetails: (String) -> Unit,
    lazyPagingItems: LazyPagingItems<GameList>
) {
    Row(modifier = Modifier.padding(top = 16.dp)) {
        StaggeredGridSearchResult(
            navigateToDetails = navigateToDetails,
            lazyPagingItems = lazyPagingItems
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun StaggeredGridSearchResult(
    navigateToDetails: (String) -> Unit,
    lazyPagingItems: LazyPagingItems<GameList>
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
                    .clickable { navigateToDetails(game.remoteId) }
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
                        rating = game.rating ?: 0f
                    )
                    EsrbRatingHolder(
                        modifier = Modifier.align(Alignment.BottomEnd),
                        esrbRating = game.esrbRating?.name.toString()
                    )
                }
                FeaturedFooter(data = game)
            }
        }
    }
}