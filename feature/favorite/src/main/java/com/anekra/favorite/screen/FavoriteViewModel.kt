package com.anekra.favorite.screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anekra.domain.repository.GameRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val repository: GameRepository,
) : ViewModel() {
    var favoriteState by mutableStateOf(FavoriteScreenState())
    
    init {
        getGameListWithScreenShots()
    }
    
    fun onEvent(event: FavoriteScreenEvent) {
        when (event) {
            is FavoriteScreenEvent.DeleteAllFavorites -> {
                viewModelScope.launch {
                    favoriteState = favoriteState.copy(isLoading = true)
                    withContext(Dispatchers.IO) {
                        repository.deleteAllGameListWithScreenShots()
                    }
                    favoriteState = favoriteState.copy(isLoading = false)
                }
            }
        }
    }
    
    private fun getGameListWithScreenShots() {
        viewModelScope.launch {
            repository.getLocalGameListWithScreenShots().collect {
                favoriteState = favoriteState.copy(isLoading = true)
                favoriteState = favoriteState.copy(favoriteGames = it, isLoading = false)
            }
        }
    }
}