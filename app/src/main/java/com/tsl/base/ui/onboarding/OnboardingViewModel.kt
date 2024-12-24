package com.tsl.base.ui.onboarding

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.tsl.base.domain.UserRepository
import com.tsl.base.ui.base.BaseViewModel
import com.tsl.base.ui.base.ProgressViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * @author md-azizul-islam
 * Created 12/23/24 at 5:30 PM
 */
@HiltViewModel
class OnboardingViewModel @Inject constructor(
    progressViewModel: ProgressViewModel, private val userRepository: UserRepository
) : BaseViewModel(progressViewModel) {

    fun getUser() {
        viewModelScope.launch {
            val response = callGenericApi { userRepository.getUsers(1) }
            response?.let {
                Log.e("kdlfkldf", "Value is here :${it.body().toString()}")
            }
        }
    }

}