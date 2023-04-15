package com.anekra.capstoneapp.presentation.component.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.anekra.capstoneapp.R

@Composable
fun PlatformContent() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = MaterialTheme.colorScheme.tertiaryContainer)
            .padding(16.dp)
    ) {
        Column {
            PlatformTitle()
            LazyVerticalGrid(
                modifier = Modifier
                    .padding(top = 12.dp)
                    .height(370.dp),
                columns = GridCells.Fixed(count = 2),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                item {
                    PlatformItem(color = Color.Cyan, image = R.drawable.pc)
                }
                item {
                    PlatformItem(color = Color.Black, image = R.drawable.ps5)
                }
                item {
                    PlatformItem(color = Color.Green, image = R.drawable.xbox)
                }
                item {
                    PlatformItem(color = Color.Red, image = R.drawable.nitendo)
                }
            }
        }
    }
}

@Composable
fun PlatformTitle() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "Browse by Platform",
            fontWeight = FontWeight.Bold,
            fontSize = MaterialTheme.typography.titleLarge.fontSize,
            color = MaterialTheme.colorScheme.onTertiaryContainer
        )
        Row(
            modifier = Modifier
                .width(48.dp)
                .align(Alignment.Bottom)
                .padding(bottom = 3.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "More",
                fontWeight = FontWeight.Bold,
                fontSize = MaterialTheme.typography.bodySmall.fontSize,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
            Icon(
                imageVector = Icons.Default.KeyboardArrowRight,
                contentDescription = "Next Icon",
                tint = MaterialTheme.colorScheme.onPrimaryContainer
            )
        }
    }
}

@Composable
fun PlatformItem(
    color: Color,
    image: Int,
) {
    Box(
        modifier = Modifier
            .size(180.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(color, color.copy(alpha = 0.2f)),
                    start = Offset(x = 0f, y = 0f),
                    end = Offset(x = 300f, y = 300f)
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        Image(
            modifier = Modifier
                .fillMaxWidth(fraction = 0.8f)
                .fillMaxHeight(fraction = 0.8f),
            painter = painterResource(id = image),
            contentDescription = "Platform Image"
        )
    }
}