package com.anekra.favorite.screen

sealed class FavoriteScreenEvent {
    object DeleteAllFavorites : FavoriteScreenEvent()
}
