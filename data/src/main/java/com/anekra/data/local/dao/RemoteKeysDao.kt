package com.anekra.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.anekra.data.local.entity.paging.RemoteKeysEntity

@Dao
interface RemoteKeysDao {
    @Query("SELECT * FROM remoteKeysTable WHERE id = :id")
    suspend fun getRemoteKeys(id: String): RemoteKeysEntity
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAllRemoteKeys(remoteKeyEntities: List<RemoteKeysEntity?>)
    
    @Query("DELETE FROM remoteKeysTable")
    suspend fun deleteAllRemoteKeys()
}