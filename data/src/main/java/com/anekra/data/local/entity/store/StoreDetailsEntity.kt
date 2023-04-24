package com.anekra.data.local.entity.store

import androidx.room.PrimaryKey

data class StoreDetailsEntity(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val store: StoreItemEntity? = null,
    val url: String? = null
)
