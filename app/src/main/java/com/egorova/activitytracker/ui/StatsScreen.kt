package com.egorova.activitytracker.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.egorova.activitytracker.viewmodel.WorkoutViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StatsScreen(
    viewModel: WorkoutViewModel,
    onBack: () -> Unit
) {
    val totalMinutes by viewModel.totalDuration.collectAsState()
    val stats by viewModel.activityStats.collectAsState()

    Scaffold(
        topBar = { TopAppBar(title = { Text("Статистика тренировок") }) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
                .fillMaxSize()
        ) {
            // Общая карточка
            Card(modifier = Modifier.fillMaxWidth()) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("Всего времени:", style = MaterialTheme.typography.titleMedium)
                    Text("$totalMinutes мин", style = MaterialTheme.typography.displaySmall)
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Text("Распределение по видам:", style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(8.dp))

            // Список статистики
            LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                stats.forEach { (type, duration) ->
                    item {
                        ListItem(
                            headlineContent = { Text(type) },
                            trailingContent = { Text("$duration мин", style = MaterialTheme.typography.titleMedium) }
                        )
                        Divider()
                    }
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            Button(onClick = onBack, modifier = Modifier.fillMaxWidth()) {
                Text("Назад")
            }
        }
    }
}