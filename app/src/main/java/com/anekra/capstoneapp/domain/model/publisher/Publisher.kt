package com.anekra.capstoneapp.domain.model.publisher

data class Publisher(
    val next: String? = null,
    val previous: String? = null,
    val count: Int? = null,
    val results: List<PublisherItem>? = null,
)
