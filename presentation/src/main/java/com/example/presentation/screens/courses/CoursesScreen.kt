package com.example.presentation.screens.courses

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.presentation.uitils.UiState
import org.koin.androidx.compose.koinViewModel

@Composable
fun CoursesScreen(viewModel: CoursesViewModel = koinViewModel(), navController: NavController) {
    val state by viewModel.courses.collectAsState()

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        when (val currentState = state) {
            is UiState.Loading -> {
                CircularProgressIndicator()
            }

            is UiState.Success -> {
                val coursesModel = currentState.data
                if (coursesModel?.cours.isNullOrEmpty()) {
                    Text("No courses found")
                } else {
                    LazyColumn(modifier = Modifier.fillMaxSize()) {
                        items(coursesModel?.cours ?: emptyList()) { course ->
                            Column(modifier = Modifier.padding(16.dp)) {
                                Text(text = course.title, style = MaterialTheme.typography.titleLarge)
                                Text(text = course.price, style = MaterialTheme.typography.bodyMedium)
                            }
                        }
                    }
                }
            }

            is UiState.Error -> {
                Text(
                    text = "Error: ${currentState.message}",
                    color = MaterialTheme.colorScheme.error
                )
            }
        }
    }
}
