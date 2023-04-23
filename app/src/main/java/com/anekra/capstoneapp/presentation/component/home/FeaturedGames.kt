package com.anekra.capstoneapp.presentation.component.home

import androidx.compose.foundation.Image
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import com.anekra.capstoneapp.R
import com.anekra.capstoneapp.domain.model.game.GameList
import com.anekra.capstoneapp.presentation.screen.home.HomeViewModel
import com.anekra.capstoneapp.util.Esrb
import com.anekra.capstoneapp.util.ImageHolder
import com.anekra.capstoneapp.util.LoadingBar
import com.anekra.capstoneapp.util.StarShape

@Composable
fun FeaturedGamesContent(
    navigateToDetails: (String) -> Unit,
    viewModel: HomeViewModel,
    lazyPagingItems: LazyPagingItems<GameList>,
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
                        if (index < 20) {
                            lazyPagingItems.itemSnapshotList.items.sortedBy { list ->
                                list.rating
                            }.reversed().also { gameList ->
                                FeaturedItem(
                                    navigateToDetails = navigateToDetails,
                                    game = gameList[index]
                                )
                            }
                        } else {
                            if (index == 20) {
                                FeaturedItemLimit()
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
fun FeaturedItemLimit() {
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
            onClick = { /*TODO*/ },
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

@Composable
fun RatingHolder(
    modifier: Modifier,
    rating: Float,
) {
    Box(
        modifier = modifier
            .size(width = 70.dp, height = 30.dp)
            .clip(
                RoundedCornerShape(
                    topStart = 0.dp,
                    topEnd = 10.dp,
                    bottomStart = 10.dp,
                    bottomEnd = 0.dp
                )
            ),
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .background(
                    color = MaterialTheme.colorScheme.surface.copy(alpha = 0.5f)
                )
                .padding(4.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Text(text = rating.toString(), color = MaterialTheme.colorScheme.onSurface)
            StarShape(rating = rating)
        }
    }
}

@Composable
fun EsrbRatingHolder(
    modifier: Modifier,
    esrbRating: String,
) {
    Box(
        modifier = modifier
            .width(40.dp)
            .padding(4.dp)
            .clip(RoundedCornerShape(4.dp))
    ) {
        val esrbId = Esrb.values().find { it.rating == esrbRating }?.image
        Image(
            painter = painterResource(id = esrbId ?: R.drawable.esrb_pending),
            contentDescription = "Esrb Rating Image"
        )
    }
}

@Composable
fun FeaturedFooter(data: GameList?) {
    Column(
        modifier = Modifier.padding(8.dp)
    ) {
        Text(
            text = data?.name.toString(),
            style = TextStyle(
                fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                color = MaterialTheme.colorScheme.onSurface,
                fontWeight = FontWeight.Bold
            )
        )
        Text(
            text = data?.released?.substring(0, 4) ?: "Coming soon",
            style = TextStyle(
                fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                color = MaterialTheme.colorScheme.onSurface
            )
        )
    }
}