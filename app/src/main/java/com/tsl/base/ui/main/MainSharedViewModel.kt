package com.tsl.base.ui.main

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tsl.base.data.local.PrefKey
import com.tsl.base.data.local.PrefManager
import com.tsl.base.domain.usecase.AppStateUC
import com.tsl.base.ui.base.BaseViewModel
import com.tsl.base.ui.base.ProgressViewModel
import com.tsl.base.ui.navgraph.Navigation
import com.tsl.base.ui.navgraph.Route
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * @author md-azizul-islam
 * Created 12/23/24 at 3:57 PM
 */
@HiltViewModel
class MainSharedViewModel @Inject constructor(private val appStateUC: AppStateUC, progressViewModel: ProgressViewModel) : BaseViewModel(progressViewModel) {

    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _loading.asStateFlow()

    private val _startDestination = mutableStateOf(Navigation.OnboardingNav.nav)
    val startDestination: State<String> = _startDestination

    init {
        viewModelScope.launch {
            appStateUC.readAppState.invoke().collect { value ->
                when (value) {
                    Navigation.OnboardingNav.nav -> _startDestination.value = Navigation.OnboardingNav.nav
                    Navigation.DashboardNav.nav -> _startDestination.value = Navigation.DashboardNav.nav
                    Navigation.LoginNav.nav -> _startDestination.value = Navigation.LoginNav.nav
                }
            }
        }
    }
}