package com.example.newsapp.di

import android.content.Context
import com.example.newsapp.data.manager.LocalUserManagerImplementation
import com.example.newsapp.domain.manager.LocalUserManager
import com.example.newsapp.domain.usecases.app_entry.AppEntryUseCases
import com.example.newsapp.domain.usecases.app_entry.ReadAppEntry
import com.example.newsapp.domain.usecases.app_entry.SaveAppEntry
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideLocalUserManager(
        @ApplicationContext context: Context
    ): LocalUserManager {
        return LocalUserManagerImplementation(
            context = context
        )
    }

    @Provides
    @Singleton
    fun provideAppEntryUseCases(
        localUserManager: LocalUserManager
    ): AppEntryUseCases {
        return AppEntryUseCases(
            saveAppEntry = SaveAppEntry(
                localUserManager = localUserManager
            ),
            readAppEntry = ReadAppEntry(
                localUserManager = localUserManager
            )
        )
    }
}