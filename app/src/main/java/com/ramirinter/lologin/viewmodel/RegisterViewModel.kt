package com.ramirinter.lologin.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ramirinter.lologin.repository.AuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class RegisterViewModel(private val repository: AuthRepository) : ViewModel() {

    private val _registerState = MutableStateFlow<String?>(null)
    val registerState = _registerState.asStateFlow()

    fun register(
        name: String,
        username: String,
        email: String,
        password: String,
        confirmPassword: String
    ) {
        if (password != confirmPassword) {
            _registerState.value = "Las contraseñas no coinciden"
            return
        }

        viewModelScope.launch {
            try {
                val response = repository.register(name, username, email, password)
                _registerState.value = "Registro exitoso: ID -> ${response.userId}"
            } catch (e: Exception) {
                _registerState.value = "Error en el registro: ${e.message}"
            }
        }
    }
}

