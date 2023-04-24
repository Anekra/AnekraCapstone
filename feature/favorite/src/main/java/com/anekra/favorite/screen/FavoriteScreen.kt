package com.anekra.favorite.screen

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import com.anekra.domain.model.game.GameWithScreenShotsDomain
import com.anekra.favorite.component.FavoriteListContent

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