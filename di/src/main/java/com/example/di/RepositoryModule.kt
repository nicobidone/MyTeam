package com.example.di

import com.example.data.PlayerRepositoryImpl
import com.example.domain.repository.PlayerRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun playerRepository(playerRepositoryImpl: PlayerRepositoryImpl): PlayerRepository
}
