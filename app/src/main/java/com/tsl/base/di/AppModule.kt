package com.tsl.base.di

import com.tsl.base.ui.base.ProgressViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * @author md-azizul-islam
 * Created 12/24/24 at 12:28 PM
 */
@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideProgressViewModel(): ProgressViewModel = ProgressViewModel()
}