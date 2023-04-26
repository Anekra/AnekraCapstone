package com.anekra.capstoneapp.di

import androidx.lifecycle.SavedStateHandle
import androidx.room.Room
import com.anekra.data.BuildConfig
import com.anekra.data.local.db.GameListDatabase
import com.anekra.data.network.RawgApi
import com.anekra.data.repository.GameRepositoryImpl
import com.anekra.details.screen.DetailsViewModel
import com.anekra.domain.repository.GameRepository
import com.anekra.util.Constants
import com.anekra.util.Constants.BASE_URL
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val dynamicModule = module {
    single<RawgApi> {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RawgApi::class.java)
    }
    single {
        Room
            .databaseBuilder(
                androidApplication(),
                GameListDatabase::class.java,
                Constants.DATABASE_NAME
            )
            .fallbackToDestructiveMigration()
            .openHelperFactory(SupportFactory(SQLiteDatabase.getBytes(BuildConfig.PASS_PHRASE.toCharArray())))
            .build()
    }
    single<GameRepository> {
        GameRepositoryImpl(api = get(), db = get(), dao = get<GameListDatabase>().gameDao())
    }
    
    factory {(handle: SavedStateHandle) ->
        DetailsViewModel(repository = get(), savedStateHandle = handle)
    }
}