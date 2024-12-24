package com.tsl.base.ui.navgraph

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.tsl.base.ui.main.MainSharedViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.tsl.base.ui.base.ProgressViewModel
import com.tsl.base.ui.common.ProgressView
import com.tsl.base.ui.onboarding.OnboardingScreen

/**
 * @author md-azizul-islam
 * Created 12/23/24 at 4:00 PM
 */
@Composable
fun NavGraph(mainViewModel: MainSharedViewModel) {
    val navController = rememberNavController()
    val snackBarHostState = remember { SnackbarHostState() }
    val progressViewModel: ProgressViewModel = hiltViewModel()
    val isProgressVisible by progressViewModel.isProgressVisible.collectAsState()
    val showSnackBar by progressViewModel.showSnackBar.observeAsState()
    Box(modifier = Modifier.fillMaxSize()) {
        NavHost(navController = navController, startDestination = mainViewModel.startDestination.destinationNav) {
            navigation(route = Navigation.OnboardingNav.nav, startDestination = Route.OnboardingScreen.path) {
                composable(route = Route.OnboardingScreen.path) {
                    OnboardingScreen(onClick = {})
                }
            }
        }
        ProgressView(isProgressVisible)
        // Custom SnackBar host without Scaffold
        SnackbarHost(
            hostState = snackBarHostState,
            modifier = Modifier
                .background(color = Color.White)
                .align(Alignment.BottomCenter)
                .padding(16.dp)
        )
    }

    LaunchedEffect(showSnackBar) {
        showSnackBar?.getContentIfNotHandled()?.let {
            snackBarHostState.showSnackbar(message = it, duration = SnackbarDuration.Short)
        }
    }

}