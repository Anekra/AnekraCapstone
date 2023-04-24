package com.anekra.domain.model.game

data class GameWithScreenShotsDomain(
    val gameDetails: GameDetails?,
    val screenShots: List<ScreenShotsItem>?
)
