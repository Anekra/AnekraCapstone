package com.anekra.capstoneapp.domain.repository

import androidx.paging.PagingData
import com.anekra.capstoneapp.domain.model.GameList
import kotlinx.coroutines.flow.Flow

interface GameRepository {
    fun getGameList(
        fetchFromRemote: Boolean = false,
        queries: HashMap<String, Any>? = null
    ): Flow<PagingData<GameList>>
    
    fun searchGameList(
        query: String
    ): Flow<PagingData<GameList>>
    
    suspend fun getGameListCount(): Int
}