package com.anekra.capstoneapp.presentation.screen.details

import com.anekra.capstoneapp.domain.model.game.GameDetails
import com.anekra.capstoneapp.domain.model.game.ScreenShotsItem

data class DetailScreenState(
    val gameId: String = "",
    val game: GameDetails? = null,
    val gameScreenShots: List<ScreenShotsItem>? = null,
    val dataIsLocal: Boolean = false,
    val isFavorite: Boolean = false,
    val isLoading: Boolean = false,
    val screenShotsIsLoading: Boolean = false,
    val databaseIsLoading: Boolean = false,
    val error: String = ""
)