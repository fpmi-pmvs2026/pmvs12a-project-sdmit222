package com.egorova.activitytracker.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.egorova.activitytracker.data.Workout
import com.egorova.activitytracker.viewmodel.WorkoutViewModel
import java.text.SimpleDateFormat
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    viewModel: WorkoutViewModel,
    onNavigateToStats: () -> Unit // Колбэк для навигации
) {
    // Подписка на данные из ViewModel
    val workouts by viewModel.workouts.collectAsState(initial = emptyList())

    // Состояние для полей ввода
    var selectedType by remember { mutableStateOf("Гулять") }
    var durationText by remember { mutableStateOf("") }
    val activityTypes = listOf("Гулять", "Велосипед", "Бокс")

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Трекер активностей") },
                actions = {
                    // Кнопка для навигации (требование многооконности)
                    IconButton(onClick = onNavigateToStats) {
                        Text("📊")
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // --- БЛОК ВВОДА ---
            Text("Выберите активность:", style = MaterialTheme.typography.titleMedium)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                activityTypes.forEach { type ->
                    FilterChip(
                        selected = (selectedType == type),
                        onClick = { selectedType = type },
                        label = { Text(type) }
                    )
                }
            }

            OutlinedTextField(
                value = durationText,
                onValueChange = { durationText = it },
                label = { Text("Длительность (мин)") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth()
            )

            Button(
                onClick = {
                    val minutes = durationText.toIntOrNull() ?: 0
                    if (minutes > 0) {
                        viewModel.addWorkout(selectedType, minutes) // Сохранение в БД
                        durationText = "" // Сброс поля
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Сохранить")
            }

            Divider()

            // --- БЛОК СПИСКА ---
            Text("История активностей:", style = MaterialTheme.typography.titleMedium)

            if (workouts.isEmpty()) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text("Список пуст", color = MaterialTheme.colorScheme.outline)
                }
            } else {
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    items(workouts) { workout ->
                        WorkoutCard(workout)
                    }
                }
            }
        }
    }
}

// Отдельный компонент для красоты каждого элемента списка
@Composable
fun WorkoutCard(workout: Workout) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(workout.type, fontWeight = FontWeight.Bold)
                val date = SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.getDefault())
                    .format(Date(workout.timestamp))
                Text(date, style = MaterialTheme.typography.bodySmall)
            }
            Text("${workout.durationMinutes} мин", style = MaterialTheme.typography.titleMedium)
        }
    }
}