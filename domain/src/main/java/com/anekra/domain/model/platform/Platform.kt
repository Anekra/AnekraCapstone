package com.anekra.domain.model.platform

data class Platform(
    val requirements: Requirements? = null,
    val releasedAt: String? = null,
    val platform: PlatformItem? = null
)
