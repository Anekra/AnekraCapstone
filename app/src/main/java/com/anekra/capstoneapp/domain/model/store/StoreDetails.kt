package com.anekra.capstoneapp.domain.model.store

data class StoreDetails(
    val id: String,
    val store: StoreItem? = null,
    val url: String? = null
)
