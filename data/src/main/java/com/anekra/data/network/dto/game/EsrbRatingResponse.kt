package com.anekra.data.network.dto.game

import com.google.gson.annotations.SerializedName

data class EsrbRatingResponse(
	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("slug")
	val slug: String? = null
)
