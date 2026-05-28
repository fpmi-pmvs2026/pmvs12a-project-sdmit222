package com.egorova.activitytracker.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "workouts")
data class Workout(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val type: String,          // "Гулять", "Велосипед" или "Бокс"
    val durationMinutes: Int,  // Длительность в минутах
    val timestamp: Long = System.currentTimeMillis() // Время добавления
)