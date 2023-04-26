package com.anekra.capstoneapp.di

import com.anekra.data.BuildConfig
import com.anekra.data.network.ApiKeyInterceptor
import com.anekra.data.network.RawgApi
import com.anekra.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.CertificatePinner
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    fun provideOkHttpClient(apiKeyInterceptor: ApiKeyInterceptor): OkHttpClient {
        val certificatePinner = CertificatePinner.Builder()
            .add(
                BuildConfig.HOST_NAME,
                BuildConfig.PIN_A,
                BuildConfig.PIN_B,
                BuildConfig.PIN_C
            )
            .build()
        val interceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        return OkHttpClient.Builder()
            .readTimeout(120, TimeUnit.SECONDS)
            .connectTimeout(120, TimeUnit.SECONDS)
            .certificatePinner(certificatePinner)
            .addInterceptor(apiKeyInterceptor)
            .addInterceptor(interceptor)
            .build()
    }
    
    @Provides
    @Singleton
    fun provideRawgApi(okHttpClient: OkHttpClient): RawgApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create()
    }
}