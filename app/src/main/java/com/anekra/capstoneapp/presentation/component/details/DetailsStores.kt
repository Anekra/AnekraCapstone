package com.anekra.capstoneapp.presentation.component.details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.anekra.capstoneapp.domain.model.game.GameDetails
import com.anekra.capstoneapp.util.calculateLazyItemsHeight

@Composable
fun DetailsStoreContent(
    game: GameDetails,
    paddingValues: PaddingValues,
) {
    val height = remember { mutableStateOf(0.dp) }
    
    LaunchedEffect(key1 = game.stores) {
        height.value = calculateLazyItemsHeight(
            itemHeight = 130.dp,
            itemCount = game.stores?.size ?: 0,
            topBarPadding = paddingValues.calculateTopPadding()
        )
    }
    
    Column(
        modifier = Modifier.padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = "Store",
            style = TextStyle(
                color = MaterialTheme.colorScheme.outline,
                fontSize = MaterialTheme.typography.bodyLarge.fontSize
            )
        )
        LazyColumn(
            modifier = Modifier.height(height.value),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            items(
                items = game.stores ?: emptyList(),
                key = {
                    it.id
                }
            ) { storeDetails ->
                Column(
                    modifier = Modifier.height(120.dp),
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                            Text(
                                text = storeDetails.store?.name ?: "Data not found",
                                style = TextStyle(
                                    color = MaterialTheme.colorScheme.onSurface,
                                    fontSize = MaterialTheme.typography.headlineLarge.fontSize
                                ),
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                            Text(
                                text = storeDetails.store?.domain ?: "Data not found",
                                style = TextStyle(
                                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f),
                                    fontSize = MaterialTheme.typography.titleMedium.fontSize
                                ),
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                        }
                        IconButton(
                            onClick = {},
                            modifier = Modifier
                                .size(50.dp)
                                .clip(shape = RoundedCornerShape(2.dp))
                                .background(
                                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                                    shape = RectangleShape
                                ),
                        ) {
                            Icon(
                                imageVector = Icons.Filled.Info,
                                contentDescription = "Store Icon",
                                tint = MaterialTheme.colorScheme.outline
                            )
                        }
                    }
                    
                    Divider(
                        color = Color.Gray,
                        thickness = 1.dp,
                        modifier = Modifier
                            .padding(top = 8.dp)
                            .fillMaxWidth()
                    )
                }
            }
        }
    }
}