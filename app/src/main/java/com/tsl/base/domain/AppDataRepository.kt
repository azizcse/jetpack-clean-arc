package com.tsl.base.domain

import kotlinx.coroutines.flow.Flow

interface AppDataRepository {
    suspend fun <T> setValue(key: String, value: T)
    fun <T> getValue(key: String, defaultValue: T): Flow<T>
    fun getNavState(): Flow<String>
}