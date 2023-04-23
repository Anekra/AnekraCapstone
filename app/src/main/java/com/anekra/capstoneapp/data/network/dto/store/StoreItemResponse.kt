package com.anekra.capstoneapp.data.network.dto.store

import com.google.gson.annotations.SerializedName

data class StoreItemResponse(
    @field:SerializedName("games_count")
    val gamesCount: Int? = null,
    
    @field:SerializedName("domain")
    val domain: String? = null,
    
    @field:SerializedName("name")
    val name: String? = null,
    
    @field:SerializedName("id")
    val id: Int? = null,
    
    @field:SerializedName("image_background")
    val imageBackground: String? = null,
    
    @field:SerializedName("slug")
    val slug: String? = null
)
