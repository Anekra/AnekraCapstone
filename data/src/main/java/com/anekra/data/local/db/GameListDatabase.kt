package com.anekra.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.anekra.data.local.dao.GameDao
import com.anekra.data.local.dao.RemoteKeysDao
import com.anekra.data.local.entity.game.GameDetailsEntity
import com.anekra.data.local.entity.game.GameListEntity
import com.anekra.data.local.entity.game.GameScreenShotsItemEntity
import com.anekra.data.local.entity.paging.RemoteKeysEntity
import com.anekra.data.local.typeconverter.TypeConverterRoom

@Database(
    entities = [
        GameListEntity::class,
        RemoteKeysEntity::class,
        GameDetailsEntity::class,
        GameScreenShotsItemEntity::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(TypeConverterRoom::class)
abstract class GameListDatabase : RoomDatabase() {
    abstract fun gameDao(): GameDao
    abstract fun remoteKeysDao(): RemoteKeysDao
   
}