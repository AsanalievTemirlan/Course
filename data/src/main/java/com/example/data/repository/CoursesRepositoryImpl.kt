package com.example.data.repository

import com.example.data.network.api.CoursesApi
import com.example.data.network.dto.toDomain
import com.example.domain.model.CoursesModel
import com.example.domain.repository.CoursesRepository

class CoursesRepositoryImpl(
    private val api: CoursesApi
): CoursesRepository {

    override suspend fun getCourses(): Result<CoursesModel> {
        return try {
            val result = api.getCourses().toDomain()
            Result.success(result)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
