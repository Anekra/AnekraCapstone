package com.anekra.domain.model.tag

data class TagItem(
    val id: String,
    val gamesCount: Int? = null,
    val name: String? = null,
    val language: String? = null,
    val imageBackground: String? = null,
    val slug: String? = null,
)
