package com.egorova.activitytracker.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.egorova.activitytracker.data.AppDatabase
import com.egorova.activitytracker.data.Workout
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class WorkoutViewModel(application: Application) : AndroidViewModel(application) {
    private val dao = AppDatabase.getDatabase(application).workoutDao()

    // Основной список тренировок
    val workouts = dao.getAllWorkouts()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    // Вычисляемая статистика: Общее время
    val totalDuration = workouts.map { list ->
        list.sumOf { it.durationMinutes }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), 0)

    // Вычисляемая статистика: Время по типам (группировка)
    val activityStats = workouts.map { list ->
        list.groupBy { it.type }
            .mapValues { entry -> entry.value.sumOf { it.durationMinutes } }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyMap())

    fun addWorkout(type: String, duration: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            dao.insertWorkout(Workout(type = type, durationMinutes = duration))
        }
    }
}