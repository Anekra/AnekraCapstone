package com.anekra.data.network.dto.platform

import com.google.gson.annotations.SerializedName

data class PlatformResponse(
    @field:SerializedName("requirements")
	val requirements: RequirementsResponse? = null,
    
    @field:SerializedName("released_at")
	val releasedAt: String? = null,
    
    @field:SerializedName("platform")
	val platform: PlatformItemResponse? = null
)
