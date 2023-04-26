package com.anekra.dynamicfeaturefavorite.navigation

import android.app.Activity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.anekra.dynamicfeaturefavorite.presentation.component.topbar.FavoriteTopBar
import com.anekra.dynamicfeaturefavorite.presentation.screen.FavoriteScreen
import com.anekra.dynamicfeaturefavorite.presentation.screen.FavoriteScreenEvent
import com.anekra.dynamicfeaturefavorite.presentation.screen.FavoriteViewModel
import com.anekra.util.*

@OptIn(ExperimentalMaterial3Api::class)
fun NavGraphBuilder.favoriteRoute(
    navHostController: NavHostController
) {
    composable(route = Screens.Favorite.route) {
        val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()
        val context = LocalContext.current
        val viewModelProvider = ViewModelProvider(context as ViewModelStoreOwner)
        val viewModel: FavoriteViewModel = viewModelProvider[FavoriteViewModel::class.java]
        val showDialog = remember { mutableStateOf(false) }

        Scaffold(
            topBar = {
                FavoriteTopBar(
                    scrollBehavior = scrollBehavior,
                    gameDetailsList = viewModel.favoriteState.favoriteGames ?: emptyList(),
                    showDialog = {
                        showDialog.value = it
                    }
                )
            }
        ) {
            if (viewModel.favoriteState.isLoading)
                LoadingBar()
            else {
                FavoriteScreen(
                    navigateToDetails = { gameId ->
                        gameId.logAsString("faviteRoute game id")
                        navHostController.navigate(Screens.FavoriteDetails.passFavoriteGameId(gameId = gameId))
                    },
                    gameDetailsList = viewModel.favoriteState.favoriteGames ?: emptyList(),
                    paddingValues = it
                )
                if (showDialog.value) {
                    AlertDialog(
                        modifier = Modifier
                            .width(250.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .background(color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.9f)),
                        onDismissRequest = { showDialog.value = false }
                    ) {
                        Column {
                            Text(modifier = Modifier.padding(20.dp), text = "Delete all list?")
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .background(color = MaterialTheme.colorScheme.surface),
                                horizontalArrangement = Arrangement.SpaceEvenly
                            ) {
                                TextButton(onClick = {
                                    showDialog.value = false
                                }) {
                                    Text(
                                        text = "Cancel",
                                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
                                    )
                                }
                                TextButton(onClick = {
                                    viewModel.onEvent(FavoriteScreenEvent.DeleteAllFavorites)
                                    showDialog.value = false
                                    showToast(
                                        message = "${viewModel.favoriteState.favoriteGames?.size} items deleted",
                                        context = context
                                    )
                                }) {
                                    Text(text = "Delete", color = Color.Red)
                                }
                            }
                        }
                    }
                }
            }
        }

        HandleBackPress {
            (context as Activity).finish()
        }
    }
}