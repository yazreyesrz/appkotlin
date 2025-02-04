package com.ramirinter.lologin.network

import com.ramirinter.lologin.model.LoginRequest
import com.ramirinter.lologin.model.LoginResponse
import com.ramirinter.lologin.model.RegisterRequest
import com.ramirinter.lologin.model.RegisterResponse
import com.ramirinter.lologin.model.TaskRequest
import com.ramirinter.lologin.model.TaskResponse
import retrofit2.http.Body
import retrofit2.http.*

interface AuthApi {
    @POST("api/v1/auth/login")
    suspend fun login(@Body request: LoginRequest): LoginResponse

    @POST("api/v1/users/create")
    suspend fun register(@Body request: RegisterRequest): RegisterResponse

}