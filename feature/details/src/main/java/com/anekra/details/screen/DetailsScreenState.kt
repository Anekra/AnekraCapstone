package com.anekra.details.screen

import com.anekra.domain.model.game.GameDetails
import com.anekra.domain.model.game.ScreenShotsItem

data class DetailsScreenState(
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