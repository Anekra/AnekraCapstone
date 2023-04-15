package com.anekra.capstoneapp.data.network.response.game

import com.google.gson.annotations.SerializedName

data class RequirementsResponse(
	@field:SerializedName("minimum")
	val minimum: String? = null,

	@field:SerializedName("recommended")
	val recommended: String? = null
)
