package com.ramirinter.lologin.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ramirinter.lologin.viewmodel.TaskViewModel

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
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = title,
            onValueChange = { title = it },
            label = { Text("Título de la tarea") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = description,
            onValueChange = { description = it },
            label = { Text("Descripción de la tarea") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { viewModel.addTask(title, description) }) {
            Text("Agregar tarea")
        }

        taskCreationState?.let { message ->
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = message)
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text("Tareas pendientes", style = androidx.compose.material3.MaterialTheme.typography.titleMedium)

        LazyColumn {
            items(tasks.size) { index ->
                val task = tasks[index]
                Text(text = "${task.title}: ${task.description}", modifier = Modifier.padding(8.dp))
            }
        }
    }
}
