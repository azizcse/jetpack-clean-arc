package com.tsl.base.domain

import retrofit2.Response

/**
 * @author md-azizul-islam
 * Created 12/24/24 at 2:22 PM
 */
interface UserRepository {
    suspend fun getUsers(userId: Int): Response<Any>
}