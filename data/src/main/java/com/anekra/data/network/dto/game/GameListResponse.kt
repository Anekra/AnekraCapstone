package com.anekra.data.network.dto.game

import com.anekra.data.network.dto.platform.PlatformResponse
import com.google.gson.annotations.SerializedName

data class GameListResponse(
	@field:SerializedName("added")
	val added: Int? = null,
	
	@field:SerializedName("suggestions_count")
	val suggestionsCount: Int? = null,
	
	@field:SerializedName("rating")
	val rating: Float? = null,
	
	@field:SerializedName("metacritic")
	val metacritic: Int? = null,
	
	@field:SerializedName("playtime")
	val playtime: Int? = null,
	
	@field:SerializedName("platforms")
	val platforms: List<PlatformResponse>? = null,
	
	@field:SerializedName("background_image")
	val backgroundImage: String? = null,
	
	@field:SerializedName("tba")
	val tba: Boolean? = null,
	
	@field:SerializedName("esrb_rating")
	val esrbRating: EsrbRatingResponse? = null,
	
	@field:SerializedName("rating_top")
	val ratingTop: Int? = null,
	
	@field:SerializedName("reviews_text_count")
	val reviewsTextCount: String? = null,
	
	@field:SerializedName("ratings")
	val ratings: List<RatingsResponse>? = null,
	
	@field:SerializedName("name")
	val name: String? = null,
	
	@field:SerializedName("id")
	val id: Int,
	
	@field:SerializedName("ratings_count")
	val ratingsCount: Int? = null,
	
	@field:SerializedName("updated")
	val updated: String? = null,
	
	@field:SerializedName("slug")
	val slug: String,
	
	@field:SerializedName("released")
	val released: String? = null
)
