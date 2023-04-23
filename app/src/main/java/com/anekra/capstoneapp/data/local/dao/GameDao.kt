package com.anekra.capstoneapp.data.local.dao

import androidx.paging.PagingSource
import androidx.room.*
import com.anekra.capstoneapp.data.local.entity.game.GameDetailsEntity
import com.anekra.capstoneapp.data.local.entity.game.GameListEntity
import com.anekra.capstoneapp.data.local.entity.game.GameScreenShotsItemEntity
import com.anekra.capstoneapp.data.local.entity.game.relationship.GameWithScreenShots
import kotlinx.coroutines.flow.Flow

@Dao
interface GameDao {
    @Query("SELECT * FROM gameListEntity")
    fun getGameListAsPagingSource(): PagingSource<Int, GameListEntity>
    
    @Query("SELECT * FROM gameListEntity WHERE LOWER(name) LIKE '%' || LOWER(:query) || '%'")
    fun searchGameList(query: String?): PagingSource<Int, GameListEntity>
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGameList(list: List<GameListEntity>)
    
    @Query("SELECT COUNT(*) FROM gameListEntity")
    suspend fun getGameListCount(): Int
    
    @Query("SELECT * FROM gameDetailsEntity")
    fun getGameDetailsList(): Flow<GameDetailsEntity>
    
    @Query("SELECT * FROM gameDetailsEntity WHERE id = :id")
    suspend fun getGameDetails(id: String): GameDetailsEntity?
    
    @Transaction
    @Query("SELECT * FROM gameDetailsEntity WHERE id = :gameDetailsId")
    fun getGameWithScreenShots(gameDetailsId: String): Flow<GameWithScreenShots>?
    
    @Transaction
    @Query("SELECT * FROM gameDetailsEntity")
    fun getGameListWithScreenShots(): Flow<List<GameWithScreenShots>>?
    
    @Transaction
    @Delete(entity = GameDetailsEntity::class)
    suspend fun deleteGameWithScreenShots(gameDetails: GameDetailsEntity)
    
    @Transaction
    @Query("DELETE FROM gameDetailsEntity")
    suspend fun deleteAllGameListWithScreenShots()
    
    @Insert(onConflict = OnConflictStrategy.REPLACE, entity = GameDetailsEntity::class)
    suspend fun addGameToFavorite(data: GameDetailsEntity)
    
    @Insert(onConflict = OnConflictStrategy.REPLACE, entity = GameScreenShotsItemEntity::class)
    suspend fun addScreenShotsToFavorite(data: List<GameScreenShotsItemEntity>)
    
    @Query(" DELETE FROM gameListEntity ")
    suspend fun deleteAllGameList()
    
}