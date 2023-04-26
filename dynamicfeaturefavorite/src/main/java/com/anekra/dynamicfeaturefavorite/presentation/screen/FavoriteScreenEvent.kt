package com.anekra.dynamicfeaturefavorite.presentation.screen

sealed class FavoriteScreenEvent {
    object DeleteAllFavorites : FavoriteScreenEvent()
}
