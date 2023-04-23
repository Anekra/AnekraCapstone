package com.anekra.capstoneapp.data.network.dto.store

import com.google.gson.annotations.SerializedName

data class StoreDetailsResponse(
    @field:SerializedName("id")
    val id: Int? = null,
    
    @field:SerializedName("store")
    val store: StoreItemResponse? = null,
    
    @field:SerializedName("url")
    val url: String? = null
)
