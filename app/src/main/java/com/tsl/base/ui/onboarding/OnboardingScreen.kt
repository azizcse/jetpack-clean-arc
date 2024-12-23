package com.tsl.base.ui.onboarding

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.tsl.base.ui.base.BaseCompose

/**
 * @author md-azizul-islam
 * Created 12/23/24 at 5:03 PM
 */
@Composable
fun OnboardingScreen(onClick: () -> Unit, modifier: Modifier = Modifier) {
    val viewModel = viewModel(modelClass = OnboardingViewModel::class.java)
    BaseCompose(baseViewModel = viewModel) {
        Column(
            modifier = modifier,
            verticalArrangement = Arrangement.Center
        ) {
            Text("Demo text")
        }
    }

}