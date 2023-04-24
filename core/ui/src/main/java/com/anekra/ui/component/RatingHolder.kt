package com.anekra.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.anekra.util.StarShape

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