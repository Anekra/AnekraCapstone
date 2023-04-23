package com.anekra.capstoneapp.domain.model.store

data class StoreItem(
    val id: String,
    val gamesCount: Int? = null,
    val domain: String? = null,
    val name: String? = null,
    val imageBackground: String? = null,
    val slug: String? = null
)
