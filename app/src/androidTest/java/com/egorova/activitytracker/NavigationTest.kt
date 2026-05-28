package com.egorova.activitytracker

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class NavigationTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun navigateToStatsScreen_andBack() {
        // 1. Ищем иконку/кнопку статистики
        composeTestRule.onNodeWithText("📊").performClick()

        // 2. Проверяем, что мы на экране статистики (там должен быть заголовок)
        composeTestRule.onNodeWithText("Статистика тренировок").assertExists()

        // 3. Проверяем кнопку назад
        composeTestRule.onNodeWithText("Назад").performClick()

        // 4. Проверяем, что вернулись на главный
        composeTestRule.onNodeWithText("Трекер активностей").assertExists()
    }
}