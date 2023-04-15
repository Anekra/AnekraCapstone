package com.anekra.capstoneapp.data.local.entity.creator

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "creatorDetailsEntity")
data class CreatorDetailsEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val name: String,
    val slug: String,
    val image: String,
    val imageBackground: String,
    val gamesCount: Int,
    val reviewCount: Int,
    val rating: String,
    val rating_top: Int,
    val updated: String
)
