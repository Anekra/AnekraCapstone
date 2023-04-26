package com.anekra.dynamicfeaturefavorite.presentation.component.topbar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import com.anekra.domain.model.game.GameWithScreenShotsDomain

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoriteTopBar(
    scrollBehavior: TopAppBarScrollBehavior,
    gameDetailsList: List<GameWithScreenShotsDomain>,
    showDialog: (Boolean) -> Unit,
) {
    TopAppBar(
        title = {
            Text(text = "Favorite")
        },
        scrollBehavior = scrollBehavior,
        actions = {
            if (gameDetailsList.isNotEmpty()) {
                IconButton(
                    onClick = {
                        showDialog(true)
                    }
                ) {
                    Icon(imageVector = Icons.Filled.Delete, contentDescription = "Delete Icon")
                }
            }
        }
    )
}