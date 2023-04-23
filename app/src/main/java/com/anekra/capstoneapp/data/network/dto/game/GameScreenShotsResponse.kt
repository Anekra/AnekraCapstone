package com.anekra.capstoneapp.data.network.dto.game

import com.google.gson.annotations.SerializedName

data class GameScreenShotsResponse(
	@field:SerializedName("next")
	val next: Any? = null,

	@field:SerializedName("previous")
	val previous: Any? = null,

	@field:SerializedName("count")
	val count: Int? = null,

	@field:SerializedName("results")
	val results: List<ScreenShotsItemResponse>? = null
)
