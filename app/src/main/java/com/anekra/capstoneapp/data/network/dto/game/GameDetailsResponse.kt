package com.anekra.capstoneapp.data.network.dto.game

import com.anekra.capstoneapp.data.network.dto.developer.DeveloperItemResponse
import com.anekra.capstoneapp.data.network.dto.genre.GenreItemResponse
import com.anekra.capstoneapp.data.network.dto.platform.PlatformResponse
import com.anekra.capstoneapp.data.network.dto.publisher.PublisherItemResponse
import com.anekra.capstoneapp.data.network.dto.store.StoreDetailsResponse
import com.anekra.capstoneapp.data.network.dto.tag.TagItemResponse
import com.google.gson.annotations.SerializedName

data class GameDetailsResponse(
    @field:SerializedName("added")
	val added: Int? = null,
    
    @field:SerializedName("rating")
	val rating: Float? = null,
    
    @field:SerializedName("description")
	val description: String? = null,
    
    @field:SerializedName("id")
	val id: Int? = null,
    
    @field:SerializedName("slug")
	val slug: String? = null,
    
    @field:SerializedName("released")
	val released: String? = null,
    
    @field:SerializedName("website")
	val website: String? = null,
    
    @field:SerializedName("background_image_additional")
	val backgroundImageAdditional: String? = null,
    
    @field:SerializedName("background_image")
	val backgroundImage: String? = null,
    
    @field:SerializedName("esrb_rating")
	val esrbRating: EsrbRatingResponse? = null,
    
    @field:SerializedName("name")
	val name: String? = null,
    
    @field:SerializedName("platforms")
	val platforms: List<PlatformResponse>? = null,
    
    @field:SerializedName("developers")
    val developers: List<DeveloperItemResponse>? = null,
    
    @field:SerializedName("genres")
    val genres: List<GenreItemResponse>? = null,
    
    @field:SerializedName("tags")
    val tags: List<TagItemResponse>? = null,
    
    @field:SerializedName("publishers")
    val publishers: List<PublisherItemResponse>? = null,
    
    @field:SerializedName("stores")
    val stores: List<StoreDetailsResponse>? = null
)