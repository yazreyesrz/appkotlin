package com.ramirinter.lologin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.ramirinter.lologin.repository.AuthRepository
import com.ramirinter.lologin.ui.theme.LologinTheme
import com.ramirinter.lologin.view.LoginScreen
import com.ramirinter.lologin.viewmodel.LoginViewModel
import com.ramirinter.lologin.viewmodel.LoginViewModelFactory

class MainActivity : ComponentActivity() {

    private val viewModel: LoginViewModel by viewModels {
        LoginViewModelFactory(AuthRepository())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LologinTheme {
                LoginScreen(viewModel, this)  // Pasar el contexto para navegar
            }
        }
    }
}
