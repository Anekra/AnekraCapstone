package com.anekra.capstoneapp.domain.model.developer

data class Developer(
    val next: String? = null,
    val previous: String? = null,
    val count: Int? = null,
    val results: List<DeveloperItem>? = null,
)