package com.anekra.capstoneapp.presentation.screen.favorite

import com.anekra.capstoneapp.domain.model.game.GameWithScreenShotsDomain

data class FavoriteScreenState(
    val favoriteGames: List<GameWithScreenShotsDomain>? = null,
    val isLoading: Boolean = false,
)