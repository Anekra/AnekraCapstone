package com.anekra.capstoneapp.data.local.entity.store

import androidx.room.PrimaryKey

data class StoreItemEntity(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val gamesCount: Int? = null,
    val domain: String? = null,
    val name: String? = null,
    val imageBackground: String? = null,
    val slug: String? = null
)
