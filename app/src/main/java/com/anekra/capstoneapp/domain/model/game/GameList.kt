package com.anekra.capstoneapp.domain.model.game

import com.anekra.capstoneapp.domain.model.platform.Platform

data class GameList(
    val remoteId: String,
    val slug: String,
    val name: String? = null,
    val released: String? = null,
    val backgroundImage: String? = null,
    val rating: Float? = null,
    val esrbRating: EsrbRating? = null,
    val platforms: List<Platform>? = null
)
