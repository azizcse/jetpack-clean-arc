package com.tsl.base.di

import com.tsl.base.data.repository.UserRepositoryImpl
import com.tsl.base.domain.UserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * @author md-azizul-islam
 * Created 12/24/24 at 2:25 PM
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun bindUserRepository(userRepositoryImpl: UserRepositoryImpl): UserRepository
}