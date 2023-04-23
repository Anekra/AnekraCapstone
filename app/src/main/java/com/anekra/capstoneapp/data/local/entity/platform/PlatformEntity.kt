package com.anekra.capstoneapp.data.local.entity.platform

import androidx.room.Entity

@Entity(tableName = "platformsItemEntity")
data class PlatformEntity(
    val requirements: RequirementsEntity? = null,
    val releasedAt: String? = null,
    val platform: PlatformItemEntity? = null
)
