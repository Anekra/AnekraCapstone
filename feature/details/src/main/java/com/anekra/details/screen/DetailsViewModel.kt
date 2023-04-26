package com.anekra.details.screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anekra.domain.Resource
import com.anekra.domain.repository.GameRepository
import com.anekra.util.Constants.GAME_ID_ARGS
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    val savedStateHandle: SavedStateHandle,
    private val repository: GameRepository,
) : ViewModel() {
    var detailsState by mutableStateOf(DetailsScreenState())
    
    init {
        getGameDetails()
    }
    
    fun onEvent(event: DetailsScreenEvent) {
        viewModelScope.launch {
            when (event) {
                is DetailsScreenEvent.AddToFavorite -> {
                    repository.apply {
                        detailsState =
                            detailsState.copy(databaseIsLoading = true, dataIsLocal = true)
                        withContext(Dispatchers.IO) {
                            detailsState.game?.let {
                                addGameToFavorite(it)
                            }
                            detailsState.gameScreenShots?.let {
                                addScreenShotsToFavorite(it, detailsState.gameId)
                            }
                        }
                        detailsState = detailsState.copy(databaseIsLoading = false)
                    }
                }
                is DetailsScreenEvent.DeleteFromFavorite -> {
                    repository.apply {
                        detailsState = detailsState.copy(databaseIsLoading = true)
                        withContext(Dispatchers.IO) {
                            deleteGameWithScreenShots(event.gameDetails)
                        }
                        detailsState =
                            detailsState.copy(databaseIsLoading = false, dataIsLocal = false)
                    }
                }
            }
        }
    }
    
    private fun getGameIdArgumentAsync(): Deferred<String?> {
        return viewModelScope.async {
            savedStateHandle.get<String>(GAME_ID_ARGS) ?: ""
        }
    }
    
    private fun getLocalGameWithScreenShots() {
        viewModelScope.launch {
            repository.getLocalGameWithScreenShots(gameDetailsId = detailsState.gameId)
                .collect {
                    detailsState = detailsState.copy(
                        game = it?.gameDetails,
                        gameScreenShots = it?.screenShots
                    )
                }
        }
    }
    
    private fun getGameDetails() {
        viewModelScope.launch {
            val getGameId = getGameIdArgumentAsync().await()
            if (getGameId?.isNotEmpty() == true) {
                detailsState = detailsState.copy(
                    gameId = getGameId,
                    dataIsLocal = repository.getLocalGameDetails(id = getGameId) != null
                )
                
                if (detailsState.dataIsLocal) {
                    getLocalGameWithScreenShots()
                } else {
                    repository.getGameDetails(id = detailsState.gameId).collect { response ->
                        detailsState = when (response) {
                            is Resource.Success ->
                                detailsState.copy(game = response.data, isLoading = false)
                            
                            is Resource.Loading ->
                                detailsState.copy(isLoading = true)
                            
                            is Resource.Error ->
                                detailsState.copy(
                                    game = null,
                                    isLoading = false,
                                    error = response.message.toString()
                                )
                        }
                    }
                    getGameScreenShots()
                }
            }
        }
    }
    
    private fun getGameScreenShots() {
        viewModelScope.launch {
            repository.getGameScreenShots(id = detailsState.gameId).collect { response ->
                detailsState = when (response) {
                    is Resource.Success ->
                        detailsState.copy(
                            gameScreenShots = response.data,
                            screenShotsIsLoading = false
                        )
                    
                    is Resource.Loading ->
                        detailsState.copy(screenShotsIsLoading = true)
                    
                    is Resource.Error ->
                        detailsState.copy(
                            game = null,
                            isLoading = false,
                            error = response.message.toString()
                        )
                }
            }
        }
    }
}