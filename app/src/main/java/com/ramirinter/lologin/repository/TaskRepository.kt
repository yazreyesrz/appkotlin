package com.ramirinter.lologin.repository

import com.ramirinter.lologin.model.Task
import com.ramirinter.lologin.model.TaskRequest
import com.ramirinter.lologin.network.TaskApi

class TaskRepository(private val api: TaskApi) {

    suspend fun getTasks(): List<Task> {
        return api.getTasks()
    }

    suspend fun createTask(task: TaskRequest): Task {
        return api.createTask(task)
    }

}
