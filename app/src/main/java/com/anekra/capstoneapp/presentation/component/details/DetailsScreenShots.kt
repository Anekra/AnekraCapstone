package com.anekra.capstoneapp.presentation.component.details

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
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
import com.anekra.capstoneapp.domain.model.game.ScreenShotsItem
import com.anekra.capstoneapp.presentation.component.home.HorizontalPagerIndicator
import com.anekra.capstoneapp.util.ImageHolder

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DetailsScreenShotsContent(
    pagerState: PagerState,
    screenShots: List<ScreenShotsItem>,
) {
    Row(
        modifier = Modifier
            .background(color = MaterialTheme.colorScheme.primaryContainer)
            .padding(16.dp)
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text(
                text = "Game ScreenShots",
                style = TextStyle(
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    fontWeight = FontWeight.Bold,
                    fontSize = MaterialTheme.typography.bodyLarge.fontSize
                )
            )
            HorizontalPager(
                modifier = Modifier
                    .height(200.dp)
                    .clip(shape = RoundedCornerShape(size = 5.dp)),
                state = pagerState,
                pageCount = screenShots.size
            ) {
                screenShots[it].image?.let { imageUrl ->
                    ImageHolder(imageUrl = imageUrl, showGradient = false)
                }
            }
            HorizontalPagerIndicator(
                pagerState = pagerState,
                pageCount = screenShots.size,
                modifier = Modifier.align(Alignment.CenterHorizontally),
                activeColor = MaterialTheme.colorScheme.onPrimaryContainer
            )
        }
    }
}