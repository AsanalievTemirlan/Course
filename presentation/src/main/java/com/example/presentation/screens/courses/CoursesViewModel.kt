package com.example.presentation.screens.courses

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.CoursesModel
import com.example.domain.usecases.CoursesUseCase
import com.example.presentation.uitils.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.core.Koin

class CoursesViewModel(
    private val getCoursesUseCase: CoursesUseCase
): ViewModel() {

    private val _courses = MutableStateFlow<UiState<CoursesModel?>>(UiState.Loading)
    val courses = _courses.asStateFlow()

    init {
        getCourses()
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
