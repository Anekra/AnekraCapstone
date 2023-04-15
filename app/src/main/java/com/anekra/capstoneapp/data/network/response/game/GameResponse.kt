package com.anekra.capstoneapp.data.network.response.game

import com.google.gson.annotations.SerializedName

data class GameResponse(
    @field:SerializedName("next")
	val next: String? = null,

	@field:SerializedName("previous")
	val previous: String? = null,

	@field:SerializedName("count")
	val count: Int? = null,

	@field:SerializedName("results")
	val results: List<GameListResponse>? = null
)
