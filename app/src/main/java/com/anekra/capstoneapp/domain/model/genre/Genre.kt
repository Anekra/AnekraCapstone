package com.anekra.capstoneapp.domain.model.genre

data class Genre(
    val next: String? = null,
    val previous: String? = null,
    val count: Int? = null,
    val results: List<GenreItem>? = null,
)
