package com.anekra.capstoneapp.navigation.screen.details

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.*
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import com.anekra.capstoneapp.R
import com.anekra.capstoneapp.navigation.screen.Screens
import com.anekra.capstoneapp.presentation.component.details.topbar.DetailsTopBar
import com.anekra.capstoneapp.presentation.screen.details.DetailsScreen
import com.anekra.capstoneapp.presentation.screen.details.DetailsScreenEvent
import com.anekra.capstoneapp.presentation.screen.details.DetailsViewModel
import com.anekra.capstoneapp.util.Constants.GAME_ID_ARGS
import com.anekra.capstoneapp.util.LoadingBar
import com.anekra.capstoneapp.util.showToast

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
        val dataIsLocal = remember { mutableStateOf(false) }
        val gameDetails = viewModel.detailsState.game
        val context = LocalContext.current
        
        LaunchedEffect(key1 = viewModel.detailsState) {
            dataIsLocal.value = viewModel.detailsState.dataIsLocal
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
                            if (dataIsLocal.value) {
                                gameDetails?.let {
                                    viewModel.onEvent(
                                        event = DetailsScreenEvent.DeleteFromFavorite(
                                            gameDetails = it
                                        )
                                    )
                                }
                                showToast(message = context.getString(R.string.deleted_from_favorite), context = context)
                            }
                            else {
                                viewModel.onEvent(event = DetailsScreenEvent.AddToFavorite)
                                showToast(message = context.getString(R.string.added_to_favorite), context = context)
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
                                tint = if (dataIsLocal.value) Color.Red
                                else Color.Gray
                            )
                    }
                }
            ) { paddingValues ->
                padding.value = paddingValues
                
                gameDetails?.let {
                    DetailsScreen(
                        game = it,
                        screenShots = viewModel.detailsState.gameScreenShots ?: emptyList(),
                        pagerState = pagerState,
                        navHostController = navHostController,
                        paddingValues = paddingValues,
                        context = context
                    )
                }
            }
        }
    }
}