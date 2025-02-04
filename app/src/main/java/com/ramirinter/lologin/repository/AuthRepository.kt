package com.ramirinter.lologin.repository

import com.ramirinter.lologin.model.LoginRequest
import com.ramirinter.lologin.model.RegisterRequest
import com.ramirinter.lologin.network.RetrofitClient
import com.ramirinter.lologin.network.RetrofitClient.authApi

class AuthRepository {
    private val authApi = RetrofitClient.authApi

    suspend fun login(email: String, password: String) = authApi.login(LoginRequest(email, password))


    suspend fun register(name: String,username: String, email: String, password: String) = RetrofitClient.authApi.register(
        RegisterRequest(name, username, email, password)
    )

}
