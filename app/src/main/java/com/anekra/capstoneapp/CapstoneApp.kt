package com.anekra.capstoneapp

import android.app.Application
import com.anekra.capstoneapp.di.dynamicModule
import dagger.hilt.android.HiltAndroidApp
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

@HiltAndroidApp
class CapstoneApp: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@CapstoneApp)
            modules(dynamicModule)
        }
    }
}