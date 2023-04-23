package com.anekra.capstoneapp.data.network.dto.store

import com.google.gson.annotations.SerializedName

data class StoreResponse(
	@field:SerializedName("next")
	val next: String? = null,

	@field:SerializedName("previous")
	val previous: String? = null,

	@field:SerializedName("count")
	val count: Int? = null,

	@field:SerializedName("results")
	val results: StoreItemResponse? = null
)