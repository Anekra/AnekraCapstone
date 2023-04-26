package com.anekra.capstoneapp.di

import android.app.Application
import androidx.room.Room
import com.anekra.data.BuildConfig
import com.anekra.data.local.dao.GameDao
import com.anekra.data.local.db.GameListDatabase
import com.anekra.util.Constants.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import net.sqlcipher.database.SupportFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    
    @Provides
    @Singleton
    fun provideDatabase(app: Application): GameListDatabase {
    
        return Room
            .databaseBuilder(
                app,
                GameListDatabase::class.java,
                DATABASE_NAME
            )
            .fallbackToDestructiveMigration()
            .openHelperFactory(SupportFactory(BuildConfig.PASS_PHRASE.toByteArray()))
            .build()
    }
    
    @Provides
    fun provideGameDao(database: GameListDatabase): GameDao = database.gameDao()
}