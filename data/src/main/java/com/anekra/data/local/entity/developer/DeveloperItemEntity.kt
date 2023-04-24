package com.anekra.data.local.entity.developer

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "developerDetailsEntity")
data class DeveloperItemEntity(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val gamesCount: Int? = null,
    val name: String? = null,
    val imageBackground: String? = null,
    val slug: String? = null
)
