package com.anekra.search.screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.anekra.domain.repository.GameRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val repository: GameRepository
) : ViewModel() {
    var searchState by mutableStateOf(SearchScreenState())
    
    init {
        searchGameList()
    }
    
    fun onEvent(event: SearchScreenEvent) {
        when (event) {
            is SearchScreenEvent.Refresh -> {
                searchGameList(query = searchState.searchQuery)
            }
            is SearchScreenEvent.OnSearch -> {
                searchGameList(query = event.query)
            }
            is SearchScreenEvent.OnSearchQueryChange -> {
                searchState = searchState.copy(searchQuery = event.query)
            }
        }
    }
    
    private fun searchGameList(
        query: String = searchState.searchQuery.lowercase(),
    ) {
        viewModelScope.launch {
            repository.searchGameList(query = query).cachedIn(viewModelScope).collect {
                searchState = searchState.copy(searchedGames = flowOf(it))
            }
        }
    }
}