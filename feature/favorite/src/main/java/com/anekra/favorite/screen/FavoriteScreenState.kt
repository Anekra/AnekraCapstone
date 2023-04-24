package com.anekra.favorite.screen

import com.anekra.domain.model.game.GameWithScreenShotsDomain

data class FavoriteScreenState(
    val favoriteGames: List<GameWithScreenShotsDomain>? = null,
    val isLoading: Boolean = false,
)