package com.anekra.capstoneapp.presentation.screen.favorite

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import com.anekra.capstoneapp.domain.model.game.GameWithScreenShotsDomain
import com.anekra.capstoneapp.presentation.component.favorite.FavoriteListContent

@Composable
fun FavoriteScreen(
    navigateToDetails: (String) -> Unit,
    gameDetailsList: List<GameWithScreenShotsDomain>,
    paddingValues: Pair<PaddingValues, PaddingValues>
) {
    FavoriteListContent(
        navigateToDetails = navigateToDetails,
        gameDetailsList = gameDetailsList,
        paddingValues = paddingValues
    )
}