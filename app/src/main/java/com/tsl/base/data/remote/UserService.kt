package com.tsl.base.data.remote

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * @author md-azizul-islam
 * Created 12/24/24 at 2:14 PM
 */
interface UserService {
    @GET("/users/{userId}")
    suspend fun getUsers(@Path("userId") userId: Int): Response<Any>
}