package com.tsl.base.domain.usecase

data class AppStateUC(
    val readAppState: ReadAppState,
    val saveAppState: SaveAppState
)