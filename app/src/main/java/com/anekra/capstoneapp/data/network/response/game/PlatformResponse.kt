package com.anekra.capstoneapp.data.network.response.game

import com.google.gson.annotations.SerializedName

data class PlatformResponse(
	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("slug")
	val slug: String? = null
)
