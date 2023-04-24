package com.anekra.details.component

import android.content.Context
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.anekra.details.R
import com.anekra.domain.model.game.GameDetails
import com.anekra.util.FiveStarShape
import com.anekra.util.formatDate
import com.anekra.util.showToast

@Composable
fun DetailsMainInfoContent(
    game: GameDetails,
    context: Context,
) {
    Row {
        Column(
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            Text(
                modifier = Modifier.padding(horizontal = 16.dp),
                text = game.name ?: stringResource(R.string.data_not_found),
                style = TextStyle(
                    color = MaterialTheme.colorScheme.onSurface,
                    fontSize = MaterialTheme.typography.titleLarge.fontSize,
                    fontWeight = FontWeight.Bold
                ),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            
            Row(modifier = Modifier.padding(horizontal = 16.dp)) {
                Column {
                    Text(
                        text = stringResource(R.string.released_),
                        style = TextStyle(
                            color = MaterialTheme.colorScheme.outline,
                            fontSize = MaterialTheme.typography.bodyLarge.fontSize
                        )
                    )
                    Text(
                        text = stringResource(R.string.developer_),
                        style = TextStyle(
                            color = MaterialTheme.colorScheme.outline,
                            fontSize = MaterialTheme.typography.bodyLarge.fontSize
                        )
                    )
                    Text(
                        text = stringResource(R.string.publisher_),
                        style = TextStyle(
                            color = MaterialTheme.colorScheme.outline,
                            fontSize = MaterialTheme.typography.bodyLarge.fontSize
                        )
                    )
                    Text(
                        text = stringResource(R.string.website_),
                        style = TextStyle(
                            color = MaterialTheme.colorScheme.outline,
                            fontSize = MaterialTheme.typography.bodyLarge.fontSize
                        )
                    )
                }
                
                Column {
                    Text(
                        text = formatDate(game.released),
                        style = TextStyle(
                            color = MaterialTheme.colorScheme.onSurface,
                            fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                        )
                    )
                    Text(
                        text = if (!game.developers.isNullOrEmpty())
                            game.developers!![0].name ?: stringResource(R.string.data_not_found)
                        else
                            stringResource(R.string.data_not_found),
                        style = TextStyle(
                            color = MaterialTheme.colorScheme.onSurface,
                            fontSize = MaterialTheme.typography.bodyLarge.fontSize
                        )
                    )
                    Text(
                        text = if (!game.publishers.isNullOrEmpty())
                            game.publishers!![0].name ?: stringResource(R.string.data_not_found)
                        else
                            stringResource(R.string.data_not_found),
                        style = TextStyle(
                            color = MaterialTheme.colorScheme.onSurface,
                            fontSize = MaterialTheme.typography.bodyLarge.fontSize
                        )
                    )
                    Text(
                        text = if (!game.website.isNullOrEmpty())
                            game.website ?: stringResource(R.string.data_not_found)
                        else
                            stringResource(R.string.data_not_found),
                        style = TextStyle(
                            color = MaterialTheme.colorScheme.onSurface,
                            fontSize = MaterialTheme.typography.bodyLarge.fontSize
                        ),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )
                }
            }
            
            Text(
                modifier = Modifier.padding(horizontal = 16.dp),
                text = if (!game.description.isNullOrEmpty()) {
                    game.description!!
                        .replace("<p>", "")
                        .substringBefore("<")
                } else
                    stringResource(R.string.data_not_found),
                style = TextStyle(
                    color = MaterialTheme.colorScheme.onSurface,
                    fontSize = MaterialTheme.typography.bodyMedium.fontSize
                )
            )
            
            Column {
                Text(
                    modifier = Modifier.padding(start = 16.dp),
                    text = stringResource(R.string.rating),
                    style = TextStyle(
                        color = MaterialTheme.colorScheme.outline,
                        fontSize = MaterialTheme.typography.bodyLarge.fontSize
                    )
                )
                
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    FiveStarShape(rating = game.rating ?: 0f)
                }
            }
            
            Column {
                Text(
                    modifier = Modifier.padding(start = 16.dp),
                    text = stringResource(R.string.tags),
                    style = TextStyle(
                        color = MaterialTheme.colorScheme.outline,
                        fontSize = MaterialTheme.typography.bodyLarge.fontSize
                    )
                )
                LazyRow(
                    contentPadding = PaddingValues(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(
                        items = game.tags ?: emptyList(),
                        key = {
                            it.id
                        }
                    ) { tag ->
                        TagItemButton(
                            text = tag.name ?: "",
                            color = MaterialTheme.colorScheme.secondary,
                            context = context
                        )
                    }
                    
                }
            }
            
            Column {
                Text(
                    modifier = Modifier.padding(start = 16.dp),
                    text = "Genres",
                    style = TextStyle(
                        color = MaterialTheme.colorScheme.outline,
                        fontSize = MaterialTheme.typography.bodyLarge.fontSize
                    )
                )
                LazyRow(
                    contentPadding = PaddingValues(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(
                        items = game.genres ?: emptyList(),
                        key = {
                            it.id
                        }
                    ) { tag ->
                        TagItemButton(
                            text = tag.name ?: "",
                            color = MaterialTheme.colorScheme.tertiary,
                            context = context
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun TagItemButton(
    text: String,
    color: Color,
    context: Context,
) {
    OutlinedButton(
        onClick = { showToast(message = text, context = context) },
        border = BorderStroke(width = 1.dp, color = color),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent,
            contentColor = color
        ),
    ) {
        Text(text = text)
    }
}