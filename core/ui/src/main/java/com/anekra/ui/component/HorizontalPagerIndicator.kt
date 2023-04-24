package com.anekra.ui.component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

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