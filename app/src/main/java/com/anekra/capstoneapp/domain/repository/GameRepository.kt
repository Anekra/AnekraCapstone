package com.anekra.capstoneapp.domain.repository

import androidx.paging.PagingData
import com.anekra.capstoneapp.domain.model.game.GameDetails
import com.anekra.capstoneapp.domain.model.game.GameList
import com.anekra.capstoneapp.domain.model.game.GameWithScreenShotsDomain
import com.anekra.capstoneapp.domain.model.game.ScreenShotsItem
import com.anekra.capstoneapp.util.Resource
import kotlinx.coroutines.flow.Flow

interface GameRepository {
    fun getGameList(
        fetchFromRemote: Boolean = false,
        queries: HashMap<String, Any>? = null,
    ): Flow<PagingData<GameList>>
    
    suspend fun getGameListCount(): Int
    
    suspend fun getLocalGameDetails(id: String): GameDetails?
    
    suspend fun addGameToFavorite(game: GameDetails)
    
    suspend fun addScreenShotsToFavorite(screenShots: List<ScreenShotsItem>, id: String)
    
    suspend fun deleteGameWithScreenShots(gameDetails: GameDetails)
    
    suspend fun deleteAllGameListWithScreenShots()
    
    fun getLocalGameWithScreenShots(gameDetailsId: String): Flow<GameWithScreenShotsDomain>
    
    fun getLocalGameListWithScreenShots(): Flow<List<GameWithScreenShotsDomain>>
    
    fun searchGameList(query: String): Flow<PagingData<GameList>>
    
    fun getGameDetails(id: String): Flow<Resource<GameDetails>>
    
    fun getGameScreenShots(id: String): Flow<Resource<List<ScreenShotsItem>>>
    
}