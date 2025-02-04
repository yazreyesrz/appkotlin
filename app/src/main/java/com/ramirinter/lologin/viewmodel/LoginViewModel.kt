package com.ramirinter.lologin.viewmodel


import android.content.Context
import android.content.Intent
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ramirinter.lologin.activities.RegisterActivity
import com.ramirinter.lologin.activities.TaskActivity
import com.ramirinter.lologin.repository.AuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LoginViewModel(private val repository: AuthRepository) : ViewModel() {

    private val _loginState = MutableStateFlow<String?>(null)
    val loginState = _loginState.asStateFlow()
    private val _loginSuccessful = MutableStateFlow(false)
    val loginSuccessful = _loginSuccessful.asStateFlow()

    fun login(email: String, password: String) {
        viewModelScope.launch {
            try {
                val response = repository.login(email, password)
                if (response.token.isNotBlank()) {
                    _loginState.value = "Login exitoso: Token -> ${response.token}"
                    _loginSuccessful.value = true  // Indicar que el login fue exitoso
                } else {
                    _loginState.value = "Error: token vacío"
                }
            } catch (e: Exception) {
                _loginState.value = "Error en el login: ${e.message}"
            }
        }
    }

    fun navigateToRegister(context: Context) {
        val intent = Intent(context, RegisterActivity::class.java)
        context.startActivity(intent)
    }

    fun navigateToTasks(context: Context) {
        val intent = Intent(context, TaskActivity::class.java)
        context.startActivity(intent)
    }

}

