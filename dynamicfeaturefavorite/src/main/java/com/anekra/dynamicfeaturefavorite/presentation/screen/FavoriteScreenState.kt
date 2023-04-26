package com.anekra.dynamicfeaturefavorite.presentation.screen

import com.anekra.domain.model.game.GameWithScreenShotsDomain

data class FavoriteScreenState(
    val favoriteGames: List<GameWithScreenShotsDomain>? = null,
    val isLoading: Boolean = false,
)