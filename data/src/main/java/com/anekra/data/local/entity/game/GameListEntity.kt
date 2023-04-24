package com.anekra.data.local.entity.game

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.anekra.data.local.entity.platform.PlatformEntity

@Entity(tableName = "gameListEntity")
data class GameListEntity(
    @PrimaryKey(autoGenerate = false)
    val remoteId: String,
    val slug: String,
    val name: String? = null,
    val released: String? = null,
    val backgroundImage: String? = null,
    val rating: Float? = null,
    val esrbRating: EsrbRatingEntity? = null,
    val platforms: List<PlatformEntity>? = null
)
