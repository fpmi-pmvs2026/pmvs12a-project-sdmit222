package com.egorova.activitytracker

import org.junit.Assert.assertEquals
import org.junit.Test

class ExampleUnitTest {

    @Test
    fun testDurationCalculation() {
        // Допустим, у вас есть список тренировок с длительностью
        val workoutDurations = listOf(30, 45, 15)

        // Логика, которую вы хотите проверить
        val total = workoutDurations.sum()

        // Проверка
        assertEquals(90, total)
    }
}