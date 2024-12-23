package com.tsl.base.ui.navgraph

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.tsl.base.ui.main.MainSharedViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.tsl.base.ui.common.ProgressView
import com.tsl.base.ui.onboarding.OnboardingScreen

/**
 * @author md-azizul-islam
 * Created 12/23/24 at 4:00 PM
 */
@Composable
fun NavGraph(mainViewModel: MainSharedViewModel) {
    val navController = rememberNavController()
    val isLoading by mainViewModel.isLoading
    Box(modifier = Modifier.fillMaxSize()) {
        NavHost(navController = navController, startDestination = mainViewModel.startDestination.destinationNav) {
            navigation(route = Navigation.OnboardingNav.nav, startDestination = Route.OnboardingScreen.path) {
                composable(route = Route.OnboardingScreen.path) {
                    OnboardingScreen(onClick = {})
                }
            }
        }
        ProgressView(isLoading = isLoading)
    }

}