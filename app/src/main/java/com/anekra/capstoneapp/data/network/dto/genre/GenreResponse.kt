package com.anekra.capstoneapp.data.network.dto.genre

import com.google.gson.annotations.SerializedName

data class GenreResponse(
	@field:SerializedName("next")
	val next: String? = null,

	@field:SerializedName("previous")
	val previous: String? = null,

	@field:SerializedName("count")
	val count: Int? = null,

	@field:SerializedName("results")
	val results: List<GenreItemResponse>? = null
)