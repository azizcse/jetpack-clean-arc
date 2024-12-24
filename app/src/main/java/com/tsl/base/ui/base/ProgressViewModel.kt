package com.tsl.base.ui.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tsl.base.utils.SingleLiveEvent
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

    private val _showSnackBar = MutableLiveData<SingleLiveEvent<String>>()
    val showSnackBar: LiveData<SingleLiveEvent<String>> = _showSnackBar

    fun showProgress() {
        _isProgressVisible.value = true
    }

    fun hideProgress() {
        _isProgressVisible.value = false
    }


    fun showSnackBar(msg: String) {
        _showSnackBar.value = SingleLiveEvent(msg)
    }
}