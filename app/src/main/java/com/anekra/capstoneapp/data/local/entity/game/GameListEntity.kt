package com.anekra.capstoneapp.data.local.entity.game

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "gameListEntity")
data class GameListEntity(
    @PrimaryKey(autoGenerate = false)
    val remoteId: String,
    val slug: String,
    val name: String? = null,
    val released: String? = null,
    val tba: Boolean? = null,
    val backgroundImage: String? = null,
    val rating: Float? = null,
    val esrbRating: EsrbRatingEntity? = null,
    val platforms: List<PlatformsItemEntity>? = null
)
