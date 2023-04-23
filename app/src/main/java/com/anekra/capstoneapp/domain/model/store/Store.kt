package com.anekra.capstoneapp.domain.model.store

data class Store(
    val next: String? = null,
    val previous: String? = null,
    val count: Int? = null,
    val results: StoreItem? = null,
)
