package com.anekra.capstoneapp.di

import android.app.Application
import androidx.room.Room
import com.anekra.capstoneapp.data.local.db.GameListDatabase
import com.anekra.capstoneapp.util.Constants.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideDatabase(app: Application): GameListDatabase {
        return Room.databaseBuilder(
            app,
            GameListDatabase::class.java,
            DATABASE_NAME
        ).build()
    }
}