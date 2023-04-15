package com.anekra.capstoneapp.data.local.entity.game

import androidx.room.Entity

@Entity(tableName = "platformEntity")
data class PlatformEntity(
    val id: Int? = null,
    val name: String? = null,
    val slug: String? = null
)
