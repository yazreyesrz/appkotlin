package com.ramirinter.lologin.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ramirinter.lologin.network.RetrofitClient
import com.ramirinter.lologin.repository.TaskRepository
import retrofit2.Retrofit

class TaskViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TaskViewModel::class.java)) {
            return TaskViewModel(TaskRepository(RetrofitClient.taskApi)) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
