package com.tsl.base.ui.base

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

/**
 * @author md-azizul-islam
 * Created 12/23/24 at 2:08 PM
 */
open class BaseViewModel : ViewModel() {
    private val _isLoading = mutableStateOf(false)
    val isLoading: State<Boolean> = _isLoading

    fun setLoading(isLoading: Boolean) {
        _isLoading.value = isLoading
    }


}