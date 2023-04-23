package com.anekra.capstoneapp.domain.model.genre

data class GenreItem(
    val id: String,
    val gamesCount: Int? = null,
    val name: String? = null,
    val imageBackground: String? = null,
    val slug: String? = null,
)
