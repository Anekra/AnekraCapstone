package com.anekra.home.component

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import com.anekra.domain.model.game.GameList
import com.anekra.home.R
import com.anekra.ui.component.FeaturedFooter
import com.anekra.ui.component.RatingHolder
import com.anekra.ui.component.bottomnav.EsrbRatingHolder
import com.anekra.util.ImageHolder
import com.anekra.util.LoadingBar
import com.anekra.util.showToast

@Composable
fun FeaturedGamesContent(
    navigateToDetails: (String) -> Unit,
    viewModel: com.anekra.home.screen.HomeViewModel,
    lazyPagingItems: LazyPagingItems<GameList>,
    context: Context
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = MaterialTheme.colorScheme.primaryContainer)
    ) {
        Column(
            modifier = Modifier.padding(vertical = 16.dp)
        ) {
            FeaturedTitle()
            Spacer(modifier = Modifier.padding(vertical = 4.dp))
            if (viewModel.homeState.isLoading)
                LoadingBar(modifier = Modifier.height(260.dp))
            else {
                LazyRow(
                    contentPadding = PaddingValues(horizontal = 8.dp)
                ) {
                    val list = lazyPagingItems.itemSnapshotList.items
                    items(
                        items = list,
                        key = {
                            it.remoteId
                        }
                    ) { game ->
                        val index = list.indexOf(game)
                        if (index < 19) {
                            lazyPagingItems.itemSnapshotList.items.sortedBy { list ->
                                list.rating
                            }.reversed().also { gameList ->
                                FeaturedItem(
                                    navigateToDetails = navigateToDetails,
                                    game = gameList[index]
                                )
                            }
                        } else {
                            if (index == 19) {
                                FeaturedItemLimit(context = context)
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun FeaturedTitle() {
    Text(
        modifier = Modifier.padding(start = 16.dp),
        text = "Featured & Recommended",
        style = TextStyle(
            fontSize = MaterialTheme.typography.titleLarge.fontSize,
            color = MaterialTheme.colorScheme.onPrimaryContainer
        )
    )
}

@Composable
fun FeaturedItem(
    navigateToDetails: (String) -> Unit,
    game: GameList,
) {
    Column(
        modifier = Modifier
            .height(260.dp)
            .padding(horizontal = 8.dp)
            .clip(RoundedCornerShape(size = 10.dp))
            .background(color = MaterialTheme.colorScheme.surface)
            .clickable { navigateToDetails(game.remoteId) }
    ) {
        Box(
            modifier = Modifier
                .width(300.dp)
                .height(200.dp)
        ) {
            game.backgroundImage?.let {
                ImageHolder(
                    imageUrl = it,
                    showGradient = false
                )
            }
            RatingHolder(
                modifier = Modifier.align(Alignment.TopEnd),
                rating = game.rating ?: 0f
            )
            EsrbRatingHolder(
                modifier = Modifier.align(Alignment.BottomEnd),
                esrbRating = game.esrbRating?.name ?: ""
            )
        }
        FeaturedFooter(data = game)
    }
}

@Composable
fun FeaturedItemLimit(context: Context) {
    Column(
        modifier = Modifier
            .height(260.dp)
            .padding(horizontal = 8.dp)
            .clip(RoundedCornerShape(size = 10.dp))
            .background(color = MaterialTheme.colorScheme.surface),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        IconButton(
            onClick = {
                showToast(
                    message = context.getString(R.string.more),
                    context = context
                )
            },
            modifier = Modifier.padding(horizontal = 32.dp),
            colors = IconButtonDefaults.filledIconButtonColors(
                containerColor = MaterialTheme.colorScheme.onPrimaryContainer,
                contentColor = MaterialTheme.colorScheme.surface
            )
        ) {
            Icon(
                modifier = Modifier.size(24.dp),
                imageVector = Icons.Default.KeyboardArrowRight,
                contentDescription = "More Icon"
            )
        }
        Text(text = "View more", color = MaterialTheme.colorScheme.onPrimaryContainer)
    }
}