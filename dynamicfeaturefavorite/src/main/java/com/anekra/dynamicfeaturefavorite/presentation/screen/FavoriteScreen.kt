package com.anekra.dynamicfeaturefavorite.presentation.screen

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import com.anekra.domain.model.game.GameWithScreenShotsDomain
import com.anekra.dynamicfeaturefavorite.presentation.component.FavoriteListContent

@Composable
fun FavoriteScreen(
    navigateToDetails: (String) -> Unit,
    gameDetailsList: List<GameWithScreenShotsDomain>,
    paddingValues: PaddingValues
) {
    FavoriteListContent(
        navigateToDetails = navigateToDetails,
        gameDetailsList = gameDetailsList,
        paddingValues = paddingValues
    )
}