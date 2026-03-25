package com.example.domain.usecases

import com.example.domain.repository.CoursesRepository

class CoursesUseCase(
    private val repository: CoursesRepository
) {
    suspend operator fun invoke() = repository.getCourses()
}