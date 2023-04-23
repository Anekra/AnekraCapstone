package com.anekra.capstoneapp.presentation.screen.search

import androidx.paging.compose.LazyPagingItems
import com.anekra.capstoneapp.domain.model.game.GameList

sealed class SearchScreenEvent {
    object Refresh : SearchScreenEvent()
    data class OnSearchQueryChange(val query: String, val lazyPagingItems: LazyPagingItems<GameList>) :
        SearchScreenEvent()
}