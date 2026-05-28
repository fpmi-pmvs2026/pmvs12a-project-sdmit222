package com.egorova.activitytracker

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.assertIsDisplayed
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainScreenTest {

    // Правило для запуска Activity
    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun mainScreen_isDisplayed() {
        // Проверяем, что заголовок виден
        composeTestRule.onNodeWithText("Трекер активностей").assertIsDisplayed()
        
        // Проверяем, что кнопка "Сохранить" есть
        composeTestRule.onNodeWithText("Сохранить").assertExists()
    }
}