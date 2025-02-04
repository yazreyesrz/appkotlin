package com.ramirinter.lologin.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.ramirinter.lologin.repository.AuthRepository
import com.ramirinter.lologin.view.RegisterScreen
import com.ramirinter.lologin.viewmodel.RegisterViewModel

class RegisterActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewModel = RegisterViewModel(AuthRepository())

        setContent {
            RegisterScreen(viewModel = viewModel)
        }
    }
}
