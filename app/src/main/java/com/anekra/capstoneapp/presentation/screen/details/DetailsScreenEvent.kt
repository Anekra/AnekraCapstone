package com.anekra.capstoneapp.presentation.screen.details

import com.anekra.capstoneapp.domain.model.game.GameDetails

sealed class DetailsScreenEvent {
    object AddToFavorite: DetailsScreenEvent()
    data class DeleteFromFavorite(val gameDetails: GameDetails): DetailsScreenEvent()
}