package com.anekra.search.screen

sealed class SearchScreenEvent {
    object Refresh : SearchScreenEvent()
    data class OnSearch(val query: String) : SearchScreenEvent()
    data class OnSearchQueryChange(val query: String) : SearchScreenEvent()
}