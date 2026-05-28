package com.egorova.activitytracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import com.egorova.activitytracker.ui.AppNavigation
import com.egorova.activitytracker.viewmodel.WorkoutViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // setContent — это точка входа в Jetpack Compose
        setContent {
            // 1. Инициализация ViewModel
            // Библиотека androidx.lifecycle:lifecycle-viewmodel-compose
            // автоматически создаст и привяжет ViewModel к жизненному циклу этой Activity
            val viewModel: WorkoutViewModel = viewModel()

            // 2. Вызов навигации
            // Мы передаем viewModel в AppNavigation, чтобы все экраны
            // могли обращаться к одним и тем же данным
            AppNavigation(viewModel)
        }
    }
}