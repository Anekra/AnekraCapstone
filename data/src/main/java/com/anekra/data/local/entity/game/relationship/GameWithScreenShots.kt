package com.anekra.data.local.entity.game.relationship

import androidx.room.Embedded
import androidx.room.Relation
import com.anekra.data.local.entity.game.GameDetailsEntity
import com.anekra.data.local.entity.game.GameScreenShotsItemEntity

data class GameWithScreenShots(
    @Embedded
    val gameDetails: GameDetailsEntity,
    
    @Relation(
        parentColumn = "id",
        entityColumn = "gameDetailsId"
    )
    val screenShots: List<GameScreenShotsItemEntity>
)
