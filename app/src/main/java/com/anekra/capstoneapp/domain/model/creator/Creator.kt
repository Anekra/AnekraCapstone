package com.anekra.capstoneapp.domain.model.creator

data class Creator(
    val next: String? = null,
    val previous: String? = null,
    val count: Int? = null,
    val results: List<CreatorItem>? = null,
)
