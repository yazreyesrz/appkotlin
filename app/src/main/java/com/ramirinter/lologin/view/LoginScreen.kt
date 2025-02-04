package com.ramirinter.lologin.view

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ramirinter.lologin.viewmodel.LoginViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(viewModel: LoginViewModel, context: Context) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val loginState by viewModel.loginState.collectAsState(initial = null)
    val loginSuccessful by viewModel.loginSuccessful.collectAsState()

    // Navegar a la actividad de tareas cuando el login sea exitoso
    LaunchedEffect(loginSuccessful) {
        if (loginSuccessful) {
            viewModel.navigateToTasks(context)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF0F4F8)) // Fondo gris azulado claro
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Texto de Bienvenida
        Text(
            text = "Bienvenido",
            fontSize = 32.sp,
            color = Color(0xFF1E3A8A), // Azul oscuro
            modifier = Modifier.padding(bottom = 24.dp)
        )

        // Campo de Email
        TextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email", color = Color(0xFF1E3A8A)) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.White,
                focusedIndicatorColor = Color(0xFF1E3A8A),
                unfocusedIndicatorColor = Color(0xFF1E3A8A).copy(alpha = 0.5f)
            ),
            shape = RoundedCornerShape(8.dp),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Campo de Contraseña
        TextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Contraseña", color = Color(0xFF1E3A8A)) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.White,
                focusedIndicatorColor = Color(0xFF1E3A8A),
                unfocusedIndicatorColor = Color(0xFF1E3A8A).copy(alpha = 0.5f)
            ),
            shape = RoundedCornerShape(8.dp),
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )

        // Botón de Iniciar Sesión
        Button(
            onClick = { viewModel.login(email, password) },
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF1E3A8A), // Azul oscuro
                contentColor = Color.White
            ),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text("Iniciar sesión")
        }

        // Mostrar el estado del login
        loginState?.let { message ->
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = message,
                color = if (message.contains("éxito")) Color(0xFF4CAF50) else Color(0xFFF44336) // Verde o rojo según el estado
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Botón para navegar a la pantalla de registro
        TextButton(
            onClick = { viewModel.navigateToRegister(context) },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "¿No tienes cuenta? Regístrate",
                color = Color(0xFF1E3A8A) // Azul oscuro
            )
        }
    }
}