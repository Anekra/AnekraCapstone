package com.anekra.capstoneapp.util

import androidx.room.TypeConverter
import com.anekra.capstoneapp.data.local.entity.game.EsrbRatingEntity
import com.anekra.capstoneapp.data.local.entity.game.PlatformsItemEntity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class TypeConverter {
    private val gson = Gson()
    
    @TypeConverter
    fun stringToEsrbRatingEntity(value: String?): EsrbRatingEntity? {
        val type = object : TypeToken<EsrbRatingEntity>(){}.type
        return gson.fromJson(value, type)
    }
    
    @TypeConverter
    fun esrbRatingEntityToString(data: EsrbRatingEntity?): String? {
        return gson.toJson(data)
    }
    
    @TypeConverter
    fun stringToPlatformsItem(value: String?): List<PlatformsItemEntity?> {
        val type = object : TypeToken<List<PlatformsItemEntity?>>(){}.type
        return gson.fromJson(value, type)
    }
    
    @TypeConverter
    fun platformsItemToString(data: List<PlatformsItemEntity?>): String? {
        return gson.toJson(data)
    }
}