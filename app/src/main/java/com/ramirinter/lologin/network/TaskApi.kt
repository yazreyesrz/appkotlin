// TaskApi.kt
package com.ramirinter.lologin.network

import com.ramirinter.lologin.model.Task
import com.ramirinter.lologin.model.TaskRequest
import retrofit2.http.*

interface TaskApi {
    @GET("api/v1/tasks")
    suspend fun getTasks(): List<Task>

    @POST("api/v1/tasks/create")
    suspend fun createTask(@Body task: TaskRequest): Task
}
