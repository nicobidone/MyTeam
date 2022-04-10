package com.example.di

import com.example.data.local.dao.PlayerDao
import com.example.data.local.dao.PlayerDaoImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class DaoModule {

    @Binds
    abstract fun playerDao(playerDaoImpl: PlayerDaoImpl): PlayerDao
}
