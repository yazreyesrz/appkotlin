package com.ramirinter.lologin.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.ramirinter.lologin.ui.theme.LologinTheme
import com.ramirinter.lologin.view.TaskScreen
import com.ramirinter.lologin.viewmodel.TaskViewModel
import com.ramirinter.lologin.viewmodel.TaskViewModelFactory

class TaskActivity : ComponentActivity() {

    private val viewModel: TaskViewModel by viewModels {
        TaskViewModelFactory()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LologinTheme {
                TaskScreen(viewModel)
            }
        }
    }
}
