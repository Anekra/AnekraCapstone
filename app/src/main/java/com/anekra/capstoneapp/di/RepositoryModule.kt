package com.anekra.capstoneapp.di

import com.anekra.data.repository.GameRepositoryImpl
import com.anekra.domain.repository.GameRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindGameRepository(gameRepositoryImpl: GameRepositoryImpl): GameRepository
}