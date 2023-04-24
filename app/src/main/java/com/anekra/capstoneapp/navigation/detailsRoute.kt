package com.anekra.capstoneapp.navigation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.LayoutDirection
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.anekra.details.R
import com.anekra.details.component.topbar.DetailsTopBar
import com.anekra.details.screen.DetailsScreen
import com.anekra.details.screen.DetailsScreenEvent
import com.anekra.details.screen.DetailsViewModel
import com.anekra.util.Constants.GAME_ID_ARGS
import com.anekra.util.LoadingBar
import com.anekra.util.Screens
import com.anekra.util.showToast

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
fun NavGraphBuilder.detailsRoute(
    navHostController: NavHostController,
) {
    composable(
        route = Screens.Details.route,
        arguments = listOf(
            navArgument(name = GAME_ID_ARGS) {
                type = NavType.StringType
                nullable = true
                defaultValue = null
            }
        )
    ) {
        val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()
        val padding = remember { mutableStateOf(PaddingValues()) }
        val viewModel: DetailsViewModel = hiltViewModel()
        val pagerState = rememberPagerState()
        val context = LocalContext.current
        var gameDetails by remember { mutableStateOf(viewModel.detailsState.game) }
        var screenShots by remember { mutableStateOf(viewModel.detailsState.gameScreenShots) }
        
        LaunchedEffect(key1 = viewModel.detailsState) {
            if (gameDetails == null && screenShots.isNullOrEmpty()) {
                gameDetails = viewModel.detailsState.game
                screenShots = viewModel.detailsState.gameScreenShots
            }
        }
        
        if (viewModel.detailsState.isLoading && gameDetails == null)
            LoadingBar()
        else {
            Scaffold(
                modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
                topBar = {
                    DetailsTopBar(
                        scrollBehavior = scrollBehavior,
                        onBackPressed = { navHostController.popBackStack() })
                },
                floatingActionButton = {
                    FloatingActionButton(
                        onClick = {
                            if (viewModel.detailsState.dataIsLocal) {
                                gameDetails?.let {
                                    viewModel.onEvent(
                                        event = DetailsScreenEvent.DeleteFromFavorite(
                                            gameDetails = it
                                        )
                                    )
                                }
                                showToast(
                                    message = context.getString(R.string.deleted_from_favorite),
                                    context = context
                                )
                            }
                            else {
                                viewModel.onEvent(event = DetailsScreenEvent.AddToFavorite)
                                showToast(
                                    message = context.getString(R.string.added_to_favorite),
                                    context = context
                                )
                            }
                        },
                        modifier = Modifier.padding(
                            end = padding.value.calculateEndPadding(
                                LayoutDirection.Ltr
                            )
                        )
                    ) {
                        if (viewModel.detailsState.databaseIsLoading)
                            CircularProgressIndicator()
                        else
                            Icon(
                                imageVector = Icons.Filled.Favorite,
                                contentDescription = context.getString(R.string.favorite_icon),
                                tint = if (viewModel.detailsState.dataIsLocal) Color.Red
                                else Color.Gray
                            )
                    }
                }
            ) { paddingValues ->
                padding.value = paddingValues
                
                gameDetails?.let {
                    DetailsScreen(
                        game = it,
                        screenShots = screenShots ?: emptyList(),
                        pagerState = pagerState,
                        paddingValues = paddingValues,
                        context = context
                    )
                }
            }
        }
    }
}