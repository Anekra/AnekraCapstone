package com.anekra.capstoneapp.data.local.entity.game

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "gameScreenShotsItemEntity",
    foreignKeys = [
        ForeignKey(
            entity = GameDetailsEntity::class,
            parentColumns = ["id"],
            childColumns = ["gameDetailsId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class GameScreenShotsItemEntity(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    @ColumnInfo(index = true)
    val gameDetailsId: String,
    val image: String? = null,
)
