package com.anekra.capstoneapp.data.network.dto.tag

import com.google.gson.annotations.SerializedName

data class TagResponse(
	@field:SerializedName("next")
	val next: String? = null,

	@field:SerializedName("previous")
	val previous: String? = null,

	@field:SerializedName("count")
	val count: Int? = null,

	@field:SerializedName("results")
	val results: List<TagItemResponse>? = null
)