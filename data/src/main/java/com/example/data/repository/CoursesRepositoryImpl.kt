package com.example.data.repository

import com.example.data.network.api.CoursesApi
import com.example.data.network.dto.toDomain
import com.example.domain.model.CoursesModel
import com.example.domain.repository.CoursesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class CoursesRepositoryImpl(
    private val api: CoursesApi
): CoursesRepository {

    override suspend fun getCourses(): Result<Flow<CoursesModel>> {
        return try {
            val result = api.getCourses().map { it.toDomain() }
            Result.success(result)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}

