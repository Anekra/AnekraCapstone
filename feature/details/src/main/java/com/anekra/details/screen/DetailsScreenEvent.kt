package com.anekra.details.screen

import com.anekra.domain.model.game.GameDetails

sealed class DetailsScreenEvent {
    object AddToFavorite: DetailsScreenEvent()
    data class DeleteFromFavorite(val gameDetails: GameDetails): DetailsScreenEvent()
}