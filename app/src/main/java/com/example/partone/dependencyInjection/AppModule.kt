package com.example.partone.dependencyInjection

import android.app.Application
import com.example.partone.data.manager.LocalUserManagerImplementation
import com.example.partone.domain.manager.LocalUserManager
import com.example.partone.domain.usecases.AppEntryUseCases
import com.example.partone.domain.usecases.ReadAppEntry
import com.example.partone.domain.usecases.SaveAppEntry
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideLocalUserManager(
        application: Application
    ):LocalUserManager = LocalUserManagerImplementation(application)


    @Provides
    @Singleton
    fun provideAppEntryUseCases(
        localUserManager: LocalUserManager
    ) = AppEntryUseCases(
        readAppEntry = ReadAppEntry(localUserManager),
        saveAppEntry = SaveAppEntry(localUserManager)
    )
}