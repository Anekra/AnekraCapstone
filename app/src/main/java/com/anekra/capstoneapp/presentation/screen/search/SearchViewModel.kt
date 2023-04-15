package com.anekra.capstoneapp.presentation.screen.search

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anekra.capstoneapp.domain.repository.GameRepository
import com.anekra.capstoneapp.util.logAsString
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val repository: GameRepository,
) : ViewModel() {
    var searchState by mutableStateOf(SearchScreenState())
    
    private var searchJob: Job? = null
    
    init {
        searchGameList()
    }
    
    fun onEvent(event: SearchScreenEvent) {
        when (event) {
            is SearchScreenEvent.Refresh -> {
                searchState = searchState.copy(isLoading = true)
                searchGameList()
            }
            is SearchScreenEvent.OnSearchQueryChange -> {
                searchState = searchState.copy(searchQuery = event.query, isLoading = true)
                searchJob?.cancel()
                searchJob = viewModelScope.launch {
                    delay(1000L)
                    searchGameList()
                }
            }
        }
    }
    
    private fun searchGameList(
        query: String = searchState.searchQuery.lowercase()
    ) {
        viewModelScope.launch {
            query.logAsString("SVM query")
            searchState = searchState.copy(
                searchedGames = repository.searchGameList(query = query),
                isLoading = repository.getGameListCount() == 0
            )
        }
    }
}