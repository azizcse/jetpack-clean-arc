package com.tsl.base.ui.base

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

/**
 * @author md-azizul-islam
 * Created 12/24/24 at 12:07 PM
 */
@HiltViewModel
class ProgressViewModel @Inject constructor() : ViewModel() {
    private val _isProgressVisible = MutableStateFlow(false)
    val isProgressVisible: StateFlow<Boolean> get() = _isProgressVisible

    fun showProgress() {
        _isProgressVisible.value = true
    }

    fun hideProgress() {
        _isProgressVisible.value = false
    }
}