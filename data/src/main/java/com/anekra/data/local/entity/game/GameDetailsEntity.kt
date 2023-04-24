package com.anekra.data.local.entity.game

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.anekra.data.local.entity.developer.DeveloperItemEntity
import com.anekra.data.local.entity.genre.GenreItemEntity
import com.anekra.data.local.entity.platform.PlatformEntity
import com.anekra.data.local.entity.publisher.PublisherItemEntity
import com.anekra.data.local.entity.store.StoreDetailsEntity
import com.anekra.data.local.entity.tag.TagItemEntity

@Entity(tableName = "gameDetailsEntity")
data class GameDetailsEntity(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val slug: String? = null,
    val name: String? = null,
    val released: String? = null,
    val backgroundImage: String? = null,
    val backgroundImageAdditional: String? = null,
    val description: String? = null,
    val website: String? = null,
    val rating: Float? = null,
    val added: Int? = null,
    val esrbRating: EsrbRatingEntity? = null,
    val platforms: List<PlatformEntity>? = null,
    val developers: List<DeveloperItemEntity>? = null,
    val genres: List<GenreItemEntity>? = null,
    val tags: List<TagItemEntity>? = null,
    val publishers: List<PublisherItemEntity>? = null,
    val stores: List<StoreDetailsEntity>? = null
)
