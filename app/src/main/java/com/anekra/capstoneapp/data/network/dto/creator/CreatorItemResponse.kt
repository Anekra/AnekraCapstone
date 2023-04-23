package com.anekra.capstoneapp.data.network.dto.creator

import com.google.gson.annotations.SerializedName

data class CreatorItemResponse(
    @field:SerializedName("image")
    val image: String? = null,
    
    @field:SerializedName("games_count")
    val gamesCount: Int? = null,
    
    @field:SerializedName("name")
    val name: String? = null,
    
    @field:SerializedName("id")
    val id: String? = null,
    
    @field:SerializedName("image_background")
    val imageBackground: String? = null,
    
    @field:SerializedName("slug")
    val slug: String? = null
)
