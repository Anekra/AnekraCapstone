package com.anekra.capstoneapp.presentation.screen.details

import android.content.Context
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.anekra.capstoneapp.domain.model.game.GameDetails
import com.anekra.capstoneapp.domain.model.game.ScreenShotsItem
import com.anekra.capstoneapp.presentation.component.details.*

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DetailsScreen(
    game: GameDetails,
    screenShots: List<ScreenShotsItem>,
    pagerState: PagerState,
    navHostController: NavHostController,
    paddingValues: PaddingValues,
    context: Context
) {
    Column(
        modifier = Modifier
            .padding(top = paddingValues.calculateTopPadding())
            .verticalScroll(rememberScrollState())
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        DetailsBannerContent(image = game.backgroundImageAdditional ?: "")
        DetailsMainInfoContent(game = game, context = context)
        DetailsScreenShotsContent(pagerState = pagerState, screenShots = screenShots)
        DetailsAdditionalInfoContent(game = game)
        DetailsStoreContent(game = game, paddingValues = paddingValues)
    }
}