package com.anekra.capstoneapp.data.local.dao

import androidx.paging.PagingSource
import androidx.room.*
import com.anekra.capstoneapp.data.local.entity.game.GameListEntity

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
    
    @Insert
    suspend fun insertFavorite(data: GameListEntity)
    
    @Query(" DELETE FROM gameListEntity ")
    suspend fun deleteAllGameList()
}