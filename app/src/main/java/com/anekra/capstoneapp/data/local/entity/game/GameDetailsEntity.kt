package com.anekra.capstoneapp.data.local.entity.game

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "gameDetailsEntity")
data class GameDetailsEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int? = null,
    val slug: String? = null,
    val name: String? = null,
    val released: String? = null,
    val tba: Boolean? = null,
    val backgroundImage: String? = null,
    val description: String? = null,
    val website: String? = null,
    val rating: Int? = null,
    val ratingTop: Int? = null,
    val ratings: RatingsEntity? = null,
    val reviewTextCount: String? = null,
    val added: Int? = null,
    val addedByStatus: AddedByStatusEntity? = null,
    val playTime: Int? = null,
    val suggestionsCount: Int? = null,
    val updated: String? = null,
    val esrbRating: EsrbRatingEntity? = null,
    val youtubeLink: String? = null,
    val twitchLink: String? = null,
    val platforms: List<PlatformsItemEntity>? = null
)
