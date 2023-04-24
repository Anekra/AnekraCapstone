package com.anekra.data.mapper

import com.anekra.data.local.entity.developer.DeveloperItemEntity
import com.anekra.data.local.entity.game.EsrbRatingEntity
import com.anekra.data.local.entity.game.GameDetailsEntity
import com.anekra.data.local.entity.game.GameListEntity
import com.anekra.data.local.entity.game.GameScreenShotsItemEntity
import com.anekra.data.local.entity.genre.GenreItemEntity
import com.anekra.data.local.entity.platform.PlatformEntity
import com.anekra.data.local.entity.platform.PlatformItemEntity
import com.anekra.data.local.entity.platform.RequirementsEntity
import com.anekra.data.local.entity.publisher.PublisherItemEntity
import com.anekra.data.local.entity.store.StoreDetailsEntity
import com.anekra.data.local.entity.store.StoreItemEntity
import com.anekra.data.local.entity.tag.TagItemEntity
import com.anekra.data.network.dto.developer.DeveloperItemResponse
import com.anekra.data.network.dto.game.EsrbRatingResponse
import com.anekra.data.network.dto.game.GameDetailsResponse
import com.anekra.data.network.dto.game.GameListResponse
import com.anekra.data.network.dto.game.ScreenShotsItemResponse
import com.anekra.data.network.dto.genre.GenreItemResponse
import com.anekra.data.network.dto.platform.PlatformItemResponse
import com.anekra.data.network.dto.platform.PlatformResponse
import com.anekra.data.network.dto.platform.RequirementsResponse
import com.anekra.data.network.dto.publisher.PublisherItemResponse
import com.anekra.data.network.dto.store.StoreDetailsResponse
import com.anekra.data.network.dto.store.StoreItemResponse
import com.anekra.data.network.dto.tag.TagItemResponse
import com.anekra.domain.model.developer.DeveloperItem
import com.anekra.domain.model.game.EsrbRating
import com.anekra.domain.model.game.GameDetails
import com.anekra.domain.model.game.GameList
import com.anekra.domain.model.game.ScreenShotsItem
import com.anekra.domain.model.genre.GenreItem
import com.anekra.domain.model.platform.Platform
import com.anekra.domain.model.platform.PlatformItem
import com.anekra.domain.model.platform.Requirements
import com.anekra.domain.model.publisher.PublisherItem
import com.anekra.domain.model.store.StoreDetails
import com.anekra.domain.model.store.StoreItem
import com.anekra.domain.model.tag.TagItem

fun GameListResponse.toGameList(): GameList {
    return GameList(
        remoteId = id.toString(),
        slug = slug,
        name = name,
        released = released,
        backgroundImage = backgroundImage,
        rating = rating,
        esrbRating = esrbRating?.toEsrbRating(),
        platforms = platforms?.map { it.toPlatform() }
    )
}

fun GameListEntity.toGameList(): GameList {
    return GameList(
        remoteId = remoteId,
        slug = slug,
        name = name,
        released = released,
        backgroundImage = backgroundImage,
        rating = rating,
        esrbRating = esrbRating?.toEsrbRating(),
        platforms = platforms?.map { it.toPlatform() }
    )
}

fun GameDetailsResponse.toGameDetails(): GameDetails {
    return GameDetails(
        id = id.toString(),
        slug = slug,
        name = name,
        description = description,
        website = website,
        released = released,
        backgroundImage = backgroundImage,
        backgroundImageAdditional = backgroundImageAdditional,
        rating = rating,
        added = added,
        esrbRating = esrbRating?.toEsrbRating(),
        platforms = platforms?.map { it.toPlatform() },
        developers = developers?.toListOfDeveloperItem(),
        genres = genres?.toListOfGenreItem(),
        stores = stores?.map { it.toStoreDetails() },
        tags = tags?.toListOfTagItem(),
        publishers = publishers?.toListOfPublisherItem()
    )
}

fun GameDetails.toGameDetailsEntity(): GameDetailsEntity {
    return GameDetailsEntity(
        id = id,
        slug = slug,
        name = name,
        description = description,
        website = website,
        released = released,
        backgroundImage = backgroundImage,
        backgroundImageAdditional = backgroundImageAdditional,
        rating = rating,
        added = added,
        esrbRating = esrbRating?.toEsrbRatingEntity(),
        platforms = platforms?.map { it.toPlatformEntity() },
        developers = developers?.toListOfDeveloperItemEntity(),
        genres = genres?.toListOfGenreItemEntity(),
        stores = stores?.map { it.toStoreDetailsEntity() },
        tags = tags?.toListOfTagItemEntity(),
        publishers = publishers?.toListOfPublisherItemEntity()
    )
}

fun GameDetailsEntity.toGameDetails(): GameDetails {
    return GameDetails(
        id = id,
        slug = slug,
        name = name,
        description = description,
        website = website,
        released = released,
        backgroundImage = backgroundImage,
        backgroundImageAdditional = backgroundImageAdditional,
        rating = rating,
        added = added,
        esrbRating = esrbRating?.toEsrbRating(),
        platforms = platforms?.map { it.toPlatform() },
        developers = developers?.toListOfDeveloperItemDomain(),
        genres = genres?.toListOfGenreItemDomain(),
        stores = stores?.map { it.toStoreDetails() },
        tags = tags?.toListOfTagItemDomain(),
        publishers = publishers?.toListOfPublisherItemDomain()
    )
}

fun List<ScreenShotsItemResponse>.toListOfScreenShotsItem(): List<ScreenShotsItem> {
    return this.map { screenShotsItemResponse ->
        ScreenShotsItem(
            id = screenShotsItemResponse.id.toString(),
            image = screenShotsItemResponse.image
        )
    }
}

fun List<ScreenShotsItem>.toListOfScreenShotsItemEntity(gameDetailsId: String): List<GameScreenShotsItemEntity> {
    return this.map { screenShotsItem ->
        GameScreenShotsItemEntity(
            id = screenShotsItem.id,
            gameDetailsId = gameDetailsId,
            image = screenShotsItem.image
        )
    }
}

fun List<GameScreenShotsItemEntity>.toListOfScreenShotsItemDomain(): List<ScreenShotsItem> {
    return this.map { screenShotsItemEntity ->
        ScreenShotsItem(
            id = screenShotsItemEntity.id,
            image = screenShotsItemEntity.image
        )
    }
}

fun List<GameList>.toGameListEntity(): List<GameListEntity> {
    return this.map { gameList ->
        GameListEntity(
            remoteId = gameList.remoteId,
            slug = gameList.slug,
            name = gameList.name,
            released = gameList.released,
            backgroundImage = gameList.backgroundImage,
            rating = gameList.rating,
            esrbRating = gameList.esrbRating?.toEsrbRatingEntity(),
            platforms = gameList.platforms?.map { it.toPlatformsItemEntity() }
        )
    }
}

fun EsrbRating.toEsrbRatingEntity(): EsrbRatingEntity {
    return EsrbRatingEntity(
        id = id,
        name = name,
        slug = slug
    )
}

fun EsrbRatingResponse.toEsrbRating(): EsrbRating {
    return EsrbRating(
        id = id,
        name = name,
        slug = slug
    )
}

fun EsrbRatingEntity.toEsrbRating(): EsrbRating {
    return EsrbRating(
        id = id,
        name = name,
        slug = slug
    )
}

fun Requirements.toRequirementsEntity(): RequirementsEntity {
    return RequirementsEntity(
        minimum = minimum,
        recommended = recommended
    )
}

fun RequirementsResponse.toRequirements(): Requirements {
    return Requirements(
        minimum = minimum,
        recommended = recommended
    )
}

fun RequirementsEntity.toRequirements(): Requirements {
    return Requirements(
        minimum = minimum,
        recommended = recommended
    )
}

fun PlatformItem.toPlatformItemEntity(): PlatformItemEntity {
    return PlatformItemEntity(
        id = id,
        name = name,
        slug = slug
    )
}

fun PlatformItemResponse.toPlatformItem(): PlatformItem {
    return PlatformItem(
        id = id.toString(),
        name = name,
        slug = slug
    )
}

fun PlatformItemEntity.toPlatform(): PlatformItem {
    return PlatformItem(
        id = id,
        name = name,
        slug = slug
    )
}

fun Platform.toPlatformEntity(): PlatformEntity {
    return PlatformEntity(
        requirements = requirements?.toRequirementsEntity(),
        releasedAt = releasedAt,
        platform = platform?.toPlatformItemEntity()
    )
}

fun PlatformResponse.toPlatform(): Platform {
    return Platform(
        requirements = requirements?.toRequirements(),
        releasedAt = releasedAt,
        platform = platform?.toPlatformItem(),
    )
}

fun Platform.toPlatformsItemEntity(): PlatformEntity {
    return PlatformEntity(
        requirements = requirements?.toRequirementsEntity(),
        releasedAt = releasedAt,
        platform = platform?.toPlatformItemEntity(),
    )
}

fun PlatformEntity.toPlatform(): Platform {
    return Platform(
        requirements = requirements?.toRequirements(),
        releasedAt = releasedAt,
        platform = platform?.toPlatform(),
    )
}

fun StoreDetailsResponse.toStoreDetails(): StoreDetails {
    return StoreDetails(
        id = id.toString(),
        store = store?.toStoreItem(),
        url = url
    )
}

fun StoreDetails.toStoreDetailsEntity(): StoreDetailsEntity {
    return StoreDetailsEntity(
        id = id,
        store = store?.toStoreItemEntity(),
        url = url
    )
}

fun StoreDetailsEntity.toStoreDetails(): StoreDetails {
    return StoreDetails(
        id = id,
        store = store?.toStoreItem(),
        url = url
    )
}

fun StoreItemResponse.toStoreItem(): StoreItem {
    return StoreItem(
        id = id.toString(),
        name = name,
        slug = slug,
        imageBackground = imageBackground,
        gamesCount = gamesCount,
        domain = domain
    )
}

fun StoreItemEntity.toStoreItem(): StoreItem {
    return StoreItem(
        id = id,
        name = name,
        slug = slug,
        imageBackground = imageBackground,
        gamesCount = gamesCount,
        domain = domain
    )
}

fun StoreItem.toStoreItemEntity(): StoreItemEntity {
    return StoreItemEntity(
        id = id,
        name = name,
        slug = slug,
        imageBackground = imageBackground,
        gamesCount = gamesCount,
        domain = domain
    )
}

fun List<DeveloperItemResponse>.toListOfDeveloperItem(): List<DeveloperItem> {
    return this.map { developerItemResponse ->
        DeveloperItem(
            id = developerItemResponse.id.toString(),
            name = developerItemResponse.name,
            slug = developerItemResponse.slug,
            gamesCount = developerItemResponse.gamesCount,
            imageBackground = developerItemResponse.imageBackground
        )
    }
}

fun List<DeveloperItem>.toListOfDeveloperItemEntity(): List<DeveloperItemEntity> {
    return this.map { developerItem ->
        DeveloperItemEntity(
            id = developerItem.id,
            name = developerItem.name,
            slug = developerItem.slug,
            gamesCount = developerItem.gamesCount,
            imageBackground = developerItem.imageBackground
        )
    }
}

fun List<DeveloperItemEntity>.toListOfDeveloperItemDomain(): List<DeveloperItem> {
    return this.map { developerItemEntity ->
        DeveloperItem(
            id = developerItemEntity.id,
            name = developerItemEntity.name,
            slug = developerItemEntity.slug,
            gamesCount = developerItemEntity.gamesCount,
            imageBackground = developerItemEntity.imageBackground
        )
    }
}

fun List<GenreItemResponse>.toListOfGenreItem(): List<GenreItem> {
    return this.map { genreItemResponse ->
        GenreItem(
            id = genreItemResponse.id.toString(),
            name = genreItemResponse.name,
            slug = genreItemResponse.slug,
            gamesCount = genreItemResponse.gamesCount,
            imageBackground = genreItemResponse.imageBackground
        )
    }
}

fun List<GenreItemEntity>.toListOfGenreItemDomain(): List<GenreItem> {
    return this.map { genreItemEntity ->
        GenreItem(
            id = genreItemEntity.id,
            name = genreItemEntity.name,
            slug = genreItemEntity.slug,
            gamesCount = genreItemEntity.gamesCount,
            imageBackground = genreItemEntity.imageBackground
        )
    }
}

fun List<GenreItem>.toListOfGenreItemEntity(): List<GenreItemEntity> {
    return this.map { genreItem ->
        GenreItemEntity(
            id = genreItem.id,
            name = genreItem.name,
            slug = genreItem.slug,
            gamesCount = genreItem.gamesCount,
            imageBackground = genreItem.imageBackground
        )
    }
}

fun List<TagItemResponse>.toListOfTagItem(): List<TagItem> {
    return this.map { tagItemResponse ->
        TagItem(
            id = tagItemResponse.id.toString(),
            name = tagItemResponse.name,
            slug = tagItemResponse.slug,
            gamesCount = tagItemResponse.gamesCount,
            imageBackground = tagItemResponse.imageBackground
        )
    }
}

fun List<TagItemEntity>.toListOfTagItemDomain(): List<TagItem> {
    return this.map { tagItemEntity ->
        TagItem(
            id = tagItemEntity.id,
            name = tagItemEntity.name,
            slug = tagItemEntity.slug,
            gamesCount = tagItemEntity.gamesCount,
            imageBackground = tagItemEntity.imageBackground
        )
    }
}

fun List<TagItem>.toListOfTagItemEntity(): List<TagItemEntity> {
    return this.map { tagItem ->
        TagItemEntity(
            id = tagItem.id,
            name = tagItem.name,
            slug = tagItem.slug,
            gamesCount = tagItem.gamesCount,
            imageBackground = tagItem.imageBackground
        )
    }
}

fun List<PublisherItemResponse>.toListOfPublisherItem(): List<PublisherItem> {
    return this.map { publisherItemResponse ->
        PublisherItem(
            id = publisherItemResponse.id.toString(),
            name = publisherItemResponse.name,
            slug = publisherItemResponse.slug,
            gamesCount = publisherItemResponse.gamesCount,
            imageBackground = publisherItemResponse.imageBackground
        )
    }
}

fun List<PublisherItemEntity>.toListOfPublisherItemDomain(): List<PublisherItem> {
    return this.map { publisherItemEntity ->
        PublisherItem(
            id = publisherItemEntity.id,
            name = publisherItemEntity.name,
            slug = publisherItemEntity.slug,
            gamesCount = publisherItemEntity.gamesCount,
            imageBackground = publisherItemEntity.imageBackground
        )
    }
}

fun List<PublisherItem>.toListOfPublisherItemEntity(): List<PublisherItemEntity> {
    return this.map { publisherItem ->
        PublisherItemEntity(
            id = publisherItem.id,
            name = publisherItem.name,
            slug = publisherItem.slug,
            gamesCount = publisherItem.gamesCount,
            imageBackground = publisherItem.imageBackground
        )
    }
}