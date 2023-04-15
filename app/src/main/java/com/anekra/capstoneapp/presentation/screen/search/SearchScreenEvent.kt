package com.anekra.capstoneapp.presentation.screen.search

sealed class SearchScreenEvent {
    object Refresh : SearchScreenEvent()
    data class OnSearchQueryChange(val query: String): SearchScreenEvent()
}