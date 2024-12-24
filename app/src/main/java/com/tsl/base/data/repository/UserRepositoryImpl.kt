package com.tsl.base.data.repository

import com.tsl.base.data.remote.UserService
import com.tsl.base.domain.UserRepository
import dagger.hilt.InstallIn
import retrofit2.Response
import javax.inject.Inject

/**
 * @author md-azizul-islam
 * Created 12/24/24 at 2:22 PM
 */
class UserRepositoryImpl @Inject constructor(private val userService: UserService):UserRepository {
    override suspend fun getUsers(userId:Int): Response<Any> {
        return userService.getUsers(userId)
    }
}