package com.example.presentation.screens.courses

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.CoursesModel
import com.example.domain.usecases.CoursesUseCase
import com.example.presentation.uitils.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CoursesViewModel(
    private val getCoursesUseCase: CoursesUseCase
): ViewModel() {

    private val _courses = MutableStateFlow<UiState<CoursesModel?>>(UiState.Loading)
    val courses = _courses.asStateFlow()
    private var isDescending = true

    init {
        getCourses()
    }

    fun sortCourses() {
        val currentState = _courses.value

        if (currentState is UiState.Success) {
            val currentData = currentState.data ?: return

            val sortedList = if (isDescending) {
                currentData.cours.sortedByDescending { it.publishDate }
            } else {
                currentData.cours.sortedBy { it.publishDate }
            }

            _courses.value = UiState.Success(
                currentData.copy(cours = sortedList)
            )
            isDescending = !isDescending
        }
    }

    fun changeFavoriteStatus(courseId: Int, isFavorite: Boolean) {
        val currentState = _courses.value

        if (currentState is UiState.Success) {
            val currentData = currentState.data ?: return
            val updatedList = currentData.cours.map {
                if (it.id == courseId) it.copy(hasLike = isFavorite) else it
            }

            _courses.value = UiState.Success(
                currentData.copy(cours = updatedList)
            )
        }
    }

    private fun getCourses() {
        viewModelScope.launch {
            _courses.value = UiState.Loading
            getCoursesUseCase.invoke()
                .onSuccess { data ->
                    _courses.value = UiState.Success(data)
                }
                .onFailure {
                    _courses.value = UiState.Error(it.message ?: "Unknown error")
                }
        }
    }


}
