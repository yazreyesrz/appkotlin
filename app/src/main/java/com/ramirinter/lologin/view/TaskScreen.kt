package com.ramirinter.lologin.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ramirinter.lologin.viewmodel.TaskViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskScreen(viewModel: TaskViewModel) {
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }

    val tasks by viewModel.tasks.collectAsState()
    val taskCreationState by viewModel.taskCreationState.collectAsState(initial = null)

    // Cargar las tareas al iniciar la pantalla
    LaunchedEffect(Unit) {
        viewModel.fetchTasks()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF0F4F8)) // Fondo gris azulado claro
            .padding(24.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Texto "Tareas"
        Text(
            text = "Tareas",
            fontSize = 32.sp,
            color = Color(0xFF1E3A8A), // Azul oscuro
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        // Campo de Título de la tarea
        TextField(
            value = title,
            onValueChange = { title = it },
            label = { Text("Título de la tarea", color = Color(0xFF1E3A8A)) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.White,
                focusedIndicatorColor = Color(0xFF1E3A8A),
                unfocusedIndicatorColor = Color(0xFF1E3A8A).copy(alpha = 0.5f)
            ),
            shape = RoundedCornerShape(8.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Campo de Descripción de la tarea
        TextField(
            value = description,
            onValueChange = { description = it },
            label = { Text("Descripción de la tarea", color = Color(0xFF1E3A8A)) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.White,
                focusedIndicatorColor = Color(0xFF1E3A8A),
                unfocusedIndicatorColor = Color(0xFF1E3A8A).copy(alpha = 0.5f)
            ),
            shape = RoundedCornerShape(8.dp)
        )

        // Botón de Agregar tarea
        Button(
            onClick = { viewModel.addTask(title, description) },
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF1E3A8A), // Azul oscuro
                contentColor = Color.White
            ),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text("Agregar tarea")
        }

        // Mostrar el estado de creación de la tarea
        taskCreationState?.let { message ->
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = message,
                color = if (message.contains("éxito")) Color(0xFF4CAF50) else Color(0xFFF44336) // Verde o rojo según el estado
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Texto "Tareas pendientes"
        Text(
            text = "Tareas pendientes",
            fontSize = 24.sp,
            color = Color(0xFF1E3A8A), // Azul oscuro
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Apartado para mostrar las tareas
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White, shape = RoundedCornerShape(8.dp))
                .padding(16.dp)
        ) {
            if (tasks.isEmpty()) {
                Text(
                    text = "No hay tareas pendientes",
                    color = Color(0xFF1E3A8A).copy(alpha = 0.6f),
                    modifier = Modifier.align(Alignment.Center)
                )
            } else {
                LazyColumn {
                    items(tasks) { task ->
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 8.dp)
                        ) {
                            Text(
                                text = task.title,
                                color = Color(0xFF1E3A8A),
                                fontWeight = FontWeight.Bold
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = task.description,
                                color = Color(0xFF1E3A8A).copy(alpha = 0.8f)
                            )
                        }
                    }
                }
            }
        }
    }
}