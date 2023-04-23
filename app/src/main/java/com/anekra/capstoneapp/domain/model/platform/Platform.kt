package com.anekra.capstoneapp.domain.model.platform

data class Platform(
    val requirements: Requirements? = null,
    val releasedAt: String? = null,
    val platform: PlatformItem? = null
)
