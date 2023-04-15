package com.anekra.capstoneapp.data.local.entity.game

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "gameScreenshotsEntity")
data class GameScreenshotsEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int? = null,
    val image: String? = null,
    val width: Int? = null,
    val height: Int? = null
)
