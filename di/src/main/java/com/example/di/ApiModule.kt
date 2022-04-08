package com.example.di

import com.example.data.endpoint.api.MatchApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Singleton
    @Provides
    fun matchApiProvider(): MatchApi {
        return RestClient.getRetrofit().create(MatchApi::class.java)
    }
}
