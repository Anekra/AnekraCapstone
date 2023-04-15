package com.anekra.capstoneapp.data.mapper

import com.anekra.capstoneapp.data.local.entity.game.*
import com.anekra.capstoneapp.data.network.response.game.*
import com.anekra.capstoneapp.domain.model.*

fun GameListResponse.toGameList(): GameList {
    return GameList(
        remoteId = id.toString(),
        slug = slug,
        name = name,
        released = released,
        tba = tba,
        backgroundImage = backgroundImage,
        rating = rating,
        esrbRating = esrbRating?.toEsrbRating(),
        platforms = platforms?.map { it.toPlatformsItem() }
    )
}
fun GameListEntity.toGameList(): GameList {
    return GameList(
        remoteId = remoteId,
        slug = slug,
        name = name,
        released = released,
        tba = tba,
        backgroundImage = backgroundImage,
        rating = rating,
        esrbRating = esrbRating?.toEsrbRating(),
        platforms = platforms?.map { it.toPlatformsItem() }
    )
}
fun GameListResponse.toGameListEntity(): GameListEntity {
    return GameListEntity(
        remoteId = id.toString(),
        slug = slug,
        name = name,
        released = released,
        tba = tba,
        backgroundImage = backgroundImage,
        rating = rating,
        esrbRating = esrbRating?.toEsrbRatingEntity(),
        platforms = platforms?.map { it.toPlatformsItemEntity() }
    )
}
fun List<GameListEntity>.toListOfGameList(): List<GameList> {
    return this.map { gameListEntity ->
        GameList(
            remoteId = gameListEntity.remoteId,
            slug = gameListEntity.slug,
            name = gameListEntity.name,
            released = gameListEntity.released,
            tba = gameListEntity.tba,
            backgroundImage = gameListEntity.backgroundImage,
            rating = gameListEntity.rating,
            esrbRating = gameListEntity.esrbRating?.toEsrbRating(),
            platforms = gameListEntity.platforms?.map { it.toPlatformsItem() }
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
            tba = gameList.tba,
            backgroundImage = gameList.backgroundImage,
            rating = gameList.rating,
            esrbRating = gameList.esrbRating?.toEsrbRatingEntity(),
            platforms = gameList.platforms?.map { it.toPlatformsItemEntity() }
        )
    }
}
fun Requirements.toRequirementsEntity(): RequirementsEntity {
    return RequirementsEntity(
        minimum = minimum,
        recommended = recommended
    )
}
fun RequirementsResponse.toRequirementsEntity(): RequirementsEntity {
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
fun Platform.toPlatformEntity(): PlatformEntity {
    return PlatformEntity(
        id = id,
        name = name,
        slug = slug
    )
}
fun PlatformResponse.toPlatformEntity(): PlatformEntity {
    return PlatformEntity(
        id = id,
        name = name,
        slug = slug
    )
}
fun PlatformResponse.toPlatform(): Platform {
    return Platform(
        id = id,
        name = name,
        slug = slug
    )
}
fun PlatformEntity.toPlatform(): Platform {
    return Platform(
        id = id,
        name = name,
        slug = slug
    )
}
fun EsrbRating.toEsrbRatingEntity(): EsrbRatingEntity {
    return EsrbRatingEntity(
        id = id,
        name = name,
        slug = slug
    )
}
fun EsrbRatingResponse.toEsrbRatingEntity(): EsrbRatingEntity {
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
fun PlatformsItemResponse.toPlatformsItem(): PlatformsItem {
    return PlatformsItem(
        requirements = requirements?.toRequirements(),
        releasedAt = releasedAt,
        platform = platform?.toPlatform(),
    )
}
fun PlatformsItem.toPlatformsItemEntity(): PlatformsItemEntity {
    return PlatformsItemEntity(
        requirements = requirements?.toRequirementsEntity(),
        releasedAt = releasedAt,
        platform = platform?.toPlatformEntity(),
    )
}
fun PlatformsItemResponse.toPlatformsItemEntity(): PlatformsItemEntity {
    return PlatformsItemEntity(
        requirements = requirements?.toRequirementsEntity(),
        releasedAt = releasedAt,
        platform = platform?.toPlatformEntity(),
    )
}
fun PlatformsItemEntity.toPlatformsItem(): PlatformsItem {
    return PlatformsItem(
        requirements = requirements?.toRequirements(),
        releasedAt = releasedAt,
        platform = platform?.toPlatform(),
    )
}