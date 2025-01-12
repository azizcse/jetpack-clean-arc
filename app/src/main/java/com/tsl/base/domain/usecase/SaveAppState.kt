package com.tsl.base.domain.usecase

import com.tsl.base.domain.AppDataRepository
import javax.inject.Inject

class SaveAppState @Inject constructor(private val appDataRepository: AppDataRepository) {
    suspend operator fun invoke(key: String, value: String) {
        appDataRepository.setValue(key, value)
    }
}