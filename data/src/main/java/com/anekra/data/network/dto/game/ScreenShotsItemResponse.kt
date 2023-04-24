package com.anekra.data.network.dto.game

import com.google.gson.annotations.SerializedName

data class ScreenShotsItemResponse(
    @field:SerializedName("image")
    val image: String? = null,
    
    @field:SerializedName("is_deleted")
    val isDeleted: Boolean? = null,
    
    @field:SerializedName("width")
    val width: Int? = null,
    
    @field:SerializedName("id")
    val id: Int? = null,
    
    @field:SerializedName("height")
    val height: Int? = null
)
