package com.anekra.favorite.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.anekra.domain.model.game.GameDetails
import com.anekra.domain.model.game.GameWithScreenShotsDomain
import com.anekra.ui.component.RatingHolder
import com.anekra.ui.component.bottomnav.EsrbRatingHolder
import com.anekra.util.ErrorText
import com.anekra.util.ImageHolder

@Composable
fun FavoriteListContent(
    navigateToDetails: (String) -> Unit,
    gameDetailsList: List<GameWithScreenShotsDomain>,
    paddingValues: Pair<PaddingValues, PaddingValues>,
) {
    Row(
        modifier = Modifier.padding(
            top = paddingValues.first.calculateTopPadding(),
            bottom = paddingValues.second.calculateBottomPadding()
        )
    ) {
        if (gameDetailsList.isNotEmpty()) {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(
                    items = gameDetailsList
                ) { gameWithScreenShots ->
                    gameWithScreenShots.gameDetails?.let {
                        FavoriteListItem(
                            navigateToDetails = navigateToDetails,
                            game = it
                        )
                    } ?: return@items
                }
            }
        } else {
            ErrorText(error = "Favorite is Empty")
        }
    }
}

@Composable
fun FavoriteListItem(
    navigateToDetails: (String) -> Unit,
    game: GameDetails,
) {
    Column(
        modifier = Modifier
            .height(260.dp)
            .padding(horizontal = 8.dp)
            .clip(RoundedCornerShape(size = 10.dp))
            .background(color = MaterialTheme.colorScheme.surfaceVariant)
            .clickable { navigateToDetails(game.id) }
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
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
        FavoriteItemFooter(game = game)
    }
}

@Composable
fun FavoriteItemFooter(game: GameDetails) {
    Column(
        modifier = Modifier.padding(8.dp)
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = game.name.toString(),
            style = TextStyle(
                fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                fontWeight = FontWeight.Bold
            )
        )
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = game.released?.substring(0, 4) ?: "Coming soon",
            style = TextStyle(
                fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        )
    }
}