package com.tsl.base.ui.base

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.delay
import retrofit2.Response

/**
 * @author md-azizul-islam
 * Created 12/23/24 at 2:08 PM
 */
open class BaseViewModel(private val progressViewModel: ProgressViewModel) : ViewModel() {


    suspend fun <T> callGenericApi(
        showSuccessMsg: Boolean = false,
        isShowLoader: Boolean = true,
        api: suspend () -> Response<T>
    ): Response<T>? {
        try {
            if (isShowLoader) {
                progressViewModel.showProgress()
            }
            delay(500)
            val response = api.invoke()
            if (isShowLoader) {
                progressViewModel.hideProgress()
                progressViewModel.showSnackBar("Called success")
            }
            if (response.isSuccessful && response.body() != null) {
                return response
            } else {
                //showErrorSanckBar(DEFAULT_ERROR_MSG)
            }
            return null
        } catch (e: Exception) {
            progressViewModel.hideProgress()
            e.printStackTrace()
            // showErrorSanckBar(DEFAULT_ERROR_MSG)
            return null
        }

    }


}