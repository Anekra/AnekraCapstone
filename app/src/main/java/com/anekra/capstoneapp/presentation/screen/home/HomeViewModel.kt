package com.anekra.capstoneapp.presentation.screen.home

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anekra.capstoneapp.domain.repository.GameRepository
import com.anekra.capstoneapp.util.waitForConditionOrTimeout
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: GameRepository,
    private val app: Application,
) : ViewModel() {
    var homeState by mutableStateOf(HomeScreenState())
    
    init {
        getGameList()
    }
    
    fun onEvent(event: HomeScreenEvent) {
        when (event) {
            is HomeScreenEvent.Refresh -> {
                homeState = homeState.copy(isLoading = true)
                getGameList(fetchFromRemote = true)
            }
        }
    }
    
    private fun getGameList(fetchFromRemote: Boolean = false) {
        viewModelScope.launch {
            homeState =
                homeState.copy(games = repository.getGameList(fetchFromRemote = fetchFromRemote))
            
            waitForConditionOrTimeout(
                getCondition = { repository.getGameListCount() == 0 },
                context = app
            ) { loading, error ->
                homeState = homeState.copy(
                    isLoading = loading,
                    error = error
                )
            }
        }
    }
}