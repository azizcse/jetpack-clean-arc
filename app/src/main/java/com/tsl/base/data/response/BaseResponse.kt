package com.tsl.base.data.response

/**
 * @author md-azizul-islam
 * Created 12/23/24 at 2:27 PM
 */
data class BaseResponse<T>(
    var message: String = "",
    var status: Boolean = false,
    var data: T? = null
)
