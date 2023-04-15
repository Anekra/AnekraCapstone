package com.anekra.capstoneapp.domain.model

data class GameList(
    val remoteId: String,
    val slug: String,
    val name: String? = null,
    val released: String? = null,
    val tba: Boolean? = null,
    val backgroundImage: String? = null,
    val rating: Float? = null,
    val esrbRating: EsrbRating? = null,
    val platforms: List<PlatformsItem>? = null
)
