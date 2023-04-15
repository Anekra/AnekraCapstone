package com.anekra.capstoneapp.data.local.entity.developer

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "developerListEntity")
data class DeveloperListEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val name: String,
    val slug: String,
    val gamesCount: Int,
    val imageBackground: String
)
