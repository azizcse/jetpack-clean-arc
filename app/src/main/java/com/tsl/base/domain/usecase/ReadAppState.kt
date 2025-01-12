package com.tsl.base.domain.usecase

import com.tsl.base.domain.AppDataRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ReadAppState @Inject constructor(private val appDataRepository: AppDataRepository) {
    operator fun invoke(): Flow<String> {
        return appDataRepository.getNavState()
    }
}