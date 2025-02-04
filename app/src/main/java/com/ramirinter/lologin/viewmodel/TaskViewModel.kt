// TaskViewModel.kt
package com.ramirinter.lologin.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ramirinter.lologin.model.Task
import com.ramirinter.lologin.model.TaskRequest
import com.ramirinter.lologin.repository.TaskRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class TaskViewModel(private val repository: TaskRepository) : ViewModel() {

    private val _tasks = MutableStateFlow<List<Task>>(emptyList())
    val tasks = _tasks.asStateFlow()

    private val _taskCreationState = MutableStateFlow<String?>(null)
    val taskCreationState = _taskCreationState.asStateFlow()

    fun fetchTasks() {
        viewModelScope.launch {
            try {
                val fetchedTasks = repository.getTasks()
                _tasks.value = fetchedTasks
            } catch (e: Exception) {
                _taskCreationState.value = "Error al obtener tareas: ${e.message}"
            }
        }
    }

    fun addTask(title: String, description: String) {
        if (title.isBlank() || description.isBlank()) {
            _taskCreationState.value = "El título y la descripción no pueden estar vacíos"
            return
        }

        viewModelScope.launch {
            try {
                val newTaskRequest = TaskRequest(title = title, description = description)
                repository.createTask(newTaskRequest)  // Llamar a la función con TaskRequest
                _taskCreationState.value = "Tarea creada exitosamente"
                fetchTasks()  // Actualizar la lista de tareas después de crear una nueva
            } catch (e: Exception) {
                _taskCreationState.value = "Error al crear tarea: ${e.message}"
            }
        }
    }

}
