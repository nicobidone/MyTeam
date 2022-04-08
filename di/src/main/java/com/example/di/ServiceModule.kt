package com.example.di

import com.example.data.endpoint.service.MatchService
import com.example.data.endpoint.service.MatchServiceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class ServiceModule {

    @Binds
    abstract fun matchService(matchServiceImpl: MatchServiceImpl): MatchService
}
