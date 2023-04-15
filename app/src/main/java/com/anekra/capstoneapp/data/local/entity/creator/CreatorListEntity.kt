package com.anekra.capstoneapp.data.local.entity.creator

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "creatorListEntity")
data class CreatorListEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val name: String,
    val slug: String,
    val image: String,
    val imageBackground: String,
    val gamesCount: Int
)
