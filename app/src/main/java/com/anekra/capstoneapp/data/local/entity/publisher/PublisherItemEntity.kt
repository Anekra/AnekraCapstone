package com.anekra.capstoneapp.data.local.entity.publisher

import androidx.room.PrimaryKey

data class PublisherItemEntity(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val gamesCount: Int? = null,
    val name: String? = null,
    val imageBackground: String? = null,
    val slug: String? = null
)
