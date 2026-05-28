package com.egorova.activitytracker.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.egorova.activitytracker.viewmodel.WorkoutViewModel

@Composable
fun AppNavigation(viewModel: WorkoutViewModel) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "main") {

        composable("main") {
            MainScreen(
                viewModel = viewModel,
                onNavigateToStats = { navController.navigate("stats") }
            )
        }

        composable("stats") {
            StatsScreen(
                viewModel = viewModel,
                onBack = { navController.popBackStack() }
            )
        }
    }
}