package com.anekra.capstoneapp.data.network.response.game

import com.google.gson.annotations.SerializedName

data class PlatformsItemResponse(
	@field:SerializedName("requirements")
	val requirements: RequirementsResponse? = null,
	
	@field:SerializedName("released_at")
	val releasedAt: String? = null,
	
	@field:SerializedName("platform")
	val platform: PlatformResponse? = null
)
