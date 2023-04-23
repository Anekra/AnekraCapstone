package com.anekra.capstoneapp.data.local.entity.tag

import androidx.room.PrimaryKey

data class TagItemEntity(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val gamesCount: Int? = null,
    val name: String? = null,
    val language: String? = null,
    val imageBackground: String? = null,
    val slug: String? = null,
)
