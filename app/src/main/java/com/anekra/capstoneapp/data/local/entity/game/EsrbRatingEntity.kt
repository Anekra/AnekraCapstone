package com.anekra.capstoneapp.data.local.entity.game

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "esrbRating")
data class EsrbRatingEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int? = null,
    val name: String? = null,
    val slug: String? = null
)
