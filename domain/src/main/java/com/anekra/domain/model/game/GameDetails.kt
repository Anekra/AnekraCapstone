package com.anekra.domain.model.game

import com.anekra.domain.model.developer.DeveloperItem
import com.anekra.domain.model.genre.GenreItem
import com.anekra.domain.model.platform.Platform
import com.anekra.domain.model.publisher.PublisherItem
import com.anekra.domain.model.store.StoreDetails
import com.anekra.domain.model.tag.TagItem

data class GameDetails(
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
    val esrbRating: EsrbRating? = null,
    val platforms: List<Platform>? = null,
    val developers: List<DeveloperItem>? = null,
    val genres: List<GenreItem>? = null,
    val tags: List<TagItem>? = null,
    val publishers: List<PublisherItem>? = null,
    val stores: List<StoreDetails>? = null,
)