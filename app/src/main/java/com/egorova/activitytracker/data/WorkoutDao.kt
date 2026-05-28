package com.egorova.activitytracker.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface WorkoutDao {
    // Получить все тренировки, отсортированные от новых к старым
    @Query("SELECT * FROM workouts ORDER BY timestamp DESC")
    fun getAllWorkouts(): Flow<List<Workout>>

    // Сохранить новую тренировку. suspend указывает на работу в корутинах
    @Insert
    suspend fun insertWorkout(workout: Workout): Long
}