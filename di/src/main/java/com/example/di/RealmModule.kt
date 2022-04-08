package com.example.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.realm.Realm
import io.realm.RealmConfiguration
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RealmModule {
    @Provides
    @Singleton
    fun providesRealmDatabase(@ApplicationContext context: Context): Realm {
        Realm.init(context)
        Realm.setDefaultConfiguration(
            RealmConfiguration
                .Builder()
                .name("My team project")
                .schemaVersion(REALM_VERSION)
                .deleteRealmIfMigrationNeeded()
                .build()
        )
        return Realm.getDefaultInstance()
    }

    private const val REALM_VERSION = 2L
}
