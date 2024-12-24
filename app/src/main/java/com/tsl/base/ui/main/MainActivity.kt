package com.tsl.base.ui.main

import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import com.tsl.base.ui.navgraph.NavGraph
import com.tsl.base.ui.theme.Jetpack_compose_baseTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val mainViewModel: MainSharedViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen().apply {
            setKeepOnScreenCondition(condition = { mainViewModel.loading.value })
        }
        val connectivityManager =
            getSystemService(ConnectivityManager::class.java) as ConnectivityManager
        setContent {
            val isConnected = remember { mutableStateOf(true) }
            LaunchedEffect(Unit) {
                lifecycleScope.launch {
                    monitorNetworkConnectivity(connectivityManager, isConnected)
                }
            }
            Jetpack_compose_baseTheme {
                Box(
                    modifier = Modifier
                        .background(MaterialTheme.colorScheme.background)
                        .fillMaxSize()
                ) {
                    MainApp(isConnected, mainViewModel)
                }
            }
        }
    }
}

@Composable
fun MainApp(isConnected: MutableState<Boolean>, mainViewModel: MainSharedViewModel) {
    if (!isConnected.value) {
        NoInternetDialog()
    } else {
        NavGraph(mainViewModel = mainViewModel)
    }
}

@Composable
fun NoInternetDialog() {
    AlertDialog(
        onDismissRequest = { /* Dialog dismissed */ },
        title = { Text(text = "No Internet") },
        text = { Text(text = "You are not connected to the internet. Please check your connection.") },
        confirmButton = {
            Button(onClick = { /* Retry or dismiss logic */ }) {
                Text(text = "Retry")
            }
        }
    )
}


fun monitorNetworkConnectivity(
    connectivityManager: ConnectivityManager,
    isConnected: MutableState<Boolean>
) {
    val networkCallback = object : ConnectivityManager.NetworkCallback() {
        override fun onAvailable(network: Network) {
            isConnected.value = true
        }

        override fun onLost(network: Network) {
            isConnected.value = false
        }

        override fun onCapabilitiesChanged(network: Network, networkCapabilities: NetworkCapabilities) {
            val hasInternet = networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
            isConnected.value = hasInternet
        }
    }

    val activeNetwork = connectivityManager.activeNetwork
    val activeNetworkCapabilities = connectivityManager.getNetworkCapabilities(activeNetwork)
    isConnected.value = activeNetworkCapabilities?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) == true

    connectivityManager.registerDefaultNetworkCallback(networkCallback)
}
