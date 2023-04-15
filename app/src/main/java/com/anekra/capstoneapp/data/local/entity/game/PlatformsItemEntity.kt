package com.anekra.capstoneapp.data.local.entity.game

import androidx.room.Entity

@Entity(tableName = "platformsItemEntity")
data class PlatformsItemEntity(
    val requirements: RequirementsEntity? = null,
    val releasedAt: String? = null,
    val platform: PlatformEntity? = null
)
