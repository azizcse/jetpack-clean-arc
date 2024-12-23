package com.tsl.base.ui.base

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

/**
 * @author md-azizul-islam
 * Created 12/23/24 at 5:25 PM
 */
@Composable
fun BaseCompose(baseViewModel: BaseViewModel, content: @Composable () -> Unit) {
    val isLoading = baseViewModel.isLoading.value
    Box(modifier = Modifier.fillMaxSize()) {
        // Main content
        content()

        // Progress bar overlay
        if (isLoading) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.5f)),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(color = Color.White)
            }
        }
    }
}