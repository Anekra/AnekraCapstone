package com.anekra.capstoneapp.presentation.component.home

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.anekra.capstoneapp.R
import com.anekra.capstoneapp.util.Genres
import com.anekra.capstoneapp.util.showToast
import com.anekra.capstoneapp.util.toAllCaps

@Composable
fun GenreContent(context: Context) {
    Row(
        modifier = Modifier
            .padding(top = 24.dp)
            .fillMaxWidth()
            .background(color = MaterialTheme.colorScheme.secondaryContainer)
            .padding(16.dp)
    ) {
        Column {
            GenreTitle(context = context)
            LazyVerticalGrid(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp)
                    .height(256.dp),
                columns = GridCells.Fixed(count = 2),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(count = 6) {
                    GenreItem(
                        color = Genres.values()[it].contentColor,
                        textColor = Genres.values()[it].textColor,
                        text = Genres.values()[it].name,
                        context
                    )
                }
            }
        }
    }
}

@Composable
fun GenreTitle(context: Context) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = stringResource(R.string.browse_by_genre),
            fontWeight = FontWeight.Bold,
            fontSize = MaterialTheme.typography.titleLarge.fontSize,
            color = MaterialTheme.colorScheme.onSecondaryContainer
        )
        Row(
            modifier = Modifier
                .width(48.dp)
                .align(Alignment.Bottom)
                .padding(bottom = 3.dp)
                .clickable {
                    showToast(
                        message = context.getString(R.string.more),
                        context = context
                    )
                },
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(R.string.more),
                fontWeight = FontWeight.Bold,
                fontSize = MaterialTheme.typography.bodySmall.fontSize,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
            Icon(
                imageVector = Icons.Default.KeyboardArrowRight,
                contentDescription = stringResource(R.string.next_icon),
                tint = MaterialTheme.colorScheme.onPrimaryContainer
            )
        }
    }
}

@Composable
fun GenreItem(
    color: Color,
    textColor: Color,
    text: String,
    context: Context,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .clip(RoundedCornerShape(15.dp))
            .background(color)
            .clickable {
                showToast(
                    message = text,
                    context = context
                )
            }
    ) {
        Text(
            modifier = Modifier
                .padding(horizontal = 12.dp, vertical = 24.dp)
                .align(Alignment.Center),
            text = text.toAllCaps(),
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = MaterialTheme.typography.titleLarge.fontSize,
                color = textColor,
                textAlign = TextAlign.Center
            )
        )
    }
}