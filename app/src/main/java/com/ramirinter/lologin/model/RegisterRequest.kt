package com.ramirinter.lologin.model

data class RegisterRequest(
    val name: String,
    val username: String,
    val email: String,
    val password: String
)