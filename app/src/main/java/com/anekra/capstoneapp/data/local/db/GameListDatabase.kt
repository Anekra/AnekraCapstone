package com.anekra.capstoneapp.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.anekra.capstoneapp.data.local.dao.GameDao
import com.anekra.capstoneapp.data.local.dao.RemoteKeysDao
import com.anekra.capstoneapp.data.local.entity.game.GameListEntity
import com.anekra.capstoneapp.data.local.entity.paging.RemoteKeysEntity
import com.anekra.capstoneapp.util.TypeConverter

@Database(
    entities = [GameListEntity::class, RemoteKeysEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(TypeConverter::class)
abstract class GameListDatabase : RoomDatabase() {
    abstract fun gameDao(): GameDao
    abstract fun remoteKeysDao(): RemoteKeysDao
}