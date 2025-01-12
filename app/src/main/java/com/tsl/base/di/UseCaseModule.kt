package com.tsl.base.di

import com.tsl.base.domain.AppDataRepository
import com.tsl.base.domain.usecase.AppStateUC
import com.tsl.base.domain.usecase.ReadAppState
import com.tsl.base.domain.usecase.SaveAppState
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {
    @Provides
    @Singleton
    fun provideAppStateUseCase(appDataRepository: AppDataRepository): AppStateUC {
        return AppStateUC(
            readAppState = ReadAppState(appDataRepository),
            saveAppState = SaveAppState(appDataRepository)
        )
    }
}