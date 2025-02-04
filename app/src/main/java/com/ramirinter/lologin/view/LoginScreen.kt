package com.ramirinter.lologin.view

import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ramirinter.lologin.MainActivity
import com.ramirinter.lologin.viewmodel.LoginViewModel

@Composable
fun LoginScreen(viewModel: LoginViewModel, context: Context) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val loginState by viewModel.loginState.collectAsState(initial = null)
    val loginSuccessful by viewModel.loginSuccessful.collectAsState()  // Observa el estado

    // Navegar a la actividad de tareas cuando el login sea exitoso
    LaunchedEffect(loginSuccessful) {
        if (loginSuccessful) {
            viewModel.navigateToTasks(context)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Contraseña") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { viewModel.login(email, password) }) {
            Text("Iniciar sesión")
        }

        // Mostrar el estado del login
        loginState?.let { message ->
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = message)
        }

        // Botón para navegar a la pantalla de registro
        Button(onClick = { viewModel.navigateToRegister(context) }) {
            Text("¿No tienes cuenta? Regístrate")
        }

    }
}


