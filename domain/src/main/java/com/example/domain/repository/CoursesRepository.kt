package com.example.domain.repository

import com.example.domain.model.CoursesModel
import kotlinx.coroutines.flow.Flow

interface CoursesRepository {
    suspend fun getCourses(): Result<Flow<CoursesModel>>
}