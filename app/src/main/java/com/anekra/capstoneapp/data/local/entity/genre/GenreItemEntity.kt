package com.anekra.capstoneapp.data.local.entity.genre

import androidx.room.PrimaryKey

data class GenreItemEntity(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val gamesCount: Int? = null,
    val name: String? = null,
    val imageBackground: String? = null,
    val slug: String? = null,
)
