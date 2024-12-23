package com.tsl.base.ui.main

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.tsl.base.data.local.PrefKey
import com.tsl.base.data.local.PrefManager
import com.tsl.base.ui.base.BaseViewModel
import com.tsl.base.ui.navgraph.Navigation
import com.tsl.base.ui.navgraph.Route
import kotlinx.coroutines.launch

/**
 * @author md-azizul-islam
 * Created 12/23/24 at 3:57 PM
 */
class MainSharedViewModel : BaseViewModel() {
    var startDestination by mutableStateOf(MainUiState())
        private set

    init {
        viewModelScope.launch {
            val isOnboardingNeedToSHow = PrefManager.getBool(PrefKey.KEY_ONBOARDING, true)
            isOnboardingNeedToSHow.let {
                startDestination = if (it) {
                    startDestination.copy(destinationNav = Navigation.DashboardNav.nav)
                } else {
                    startDestination.copy(destinationNav = Navigation.LoginNav.nav)
                }
            }
        }
    }
}