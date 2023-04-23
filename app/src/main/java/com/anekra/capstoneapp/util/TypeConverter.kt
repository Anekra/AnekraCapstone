package com.anekra.capstoneapp.util

import androidx.room.TypeConverter
import com.anekra.capstoneapp.data.local.entity.developer.DeveloperItemEntity
import com.anekra.capstoneapp.data.local.entity.game.EsrbRatingEntity
import com.anekra.capstoneapp.data.local.entity.genre.GenreItemEntity
import com.anekra.capstoneapp.data.local.entity.platform.PlatformEntity
import com.anekra.capstoneapp.data.local.entity.publisher.PublisherItemEntity
import com.anekra.capstoneapp.data.local.entity.store.StoreDetailsEntity
import com.anekra.capstoneapp.data.local.entity.tag.TagItemEntity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class TypeConverter {
    private val gson = Gson()
    
    @TypeConverter
    fun stringToEsrbRatingEntity(value: String?): EsrbRatingEntity? {
        val type = object : TypeToken<EsrbRatingEntity>() {}.type
        return gson.fromJson(value, type)
    }
    
    @TypeConverter
    fun esrbRatingEntityToString(data: EsrbRatingEntity?): String? {
        return gson.toJson(data)
    }
    
    @TypeConverter
    fun stringToPlatformsItem(value: String?): List<PlatformEntity>? {
        val type = object : TypeToken<List<PlatformEntity>>() {}.type
        return gson.fromJson(value, type)
    }
    
    @TypeConverter
    fun platformsItemToString(data: List<PlatformEntity>?): String? {
        return gson.toJson(data)
    }
    
    @TypeConverter
    fun stringToDeveloperItem(value: String?): List<DeveloperItemEntity>? {
        val type = object : TypeToken<List<DeveloperItemEntity>>() {}.type
        return gson.fromJson(value, type)
    }
    
    @TypeConverter
    fun developerItemToString(data: List<DeveloperItemEntity>?): String? {
        return gson.toJson(data)
    }
    
    @TypeConverter
    fun stringToGenreItem(value: String?): List<GenreItemEntity>? {
        val type = object : TypeToken<List<GenreItemEntity>>() {}.type
        return gson.fromJson(value, type)
    }
    
    @TypeConverter
    fun genreItemToString(data: List<GenreItemEntity>?): String? {
        return gson.toJson(data)
    }
    
    @TypeConverter
    fun stringToTagItem(value: String?): List<TagItemEntity>? {
        val type = object : TypeToken<List<TagItemEntity>>() {}.type
        return gson.fromJson(value, type)
    }
    
    @TypeConverter
    fun tagItemToString(data: List<TagItemEntity>?): String? {
        return gson.toJson(data)
    }
    
    @TypeConverter
    fun stringToPublisherItem(value: String?): List<PublisherItemEntity>? {
        val type = object : TypeToken<List<PublisherItemEntity>>() {}.type
        return gson.fromJson(value, type)
    }
    
    @TypeConverter
    fun publisherItemToString(data: List<PublisherItemEntity>?): String? {
        return gson.toJson(data)
    }
    
    @TypeConverter
    fun stringToStoreDetailsItem(value: String?): List<StoreDetailsEntity>? {
        val type = object : TypeToken<List<StoreDetailsEntity>>() {}.type
        return gson.fromJson(value, type)
    }
    
    @TypeConverter
    fun storeDetailsItemToString(data: List<StoreDetailsEntity>?): String? {
        return gson.toJson(data)
    }
}