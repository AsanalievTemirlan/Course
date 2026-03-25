package com.example.data.network.api

import com.example.data.network.dto.CourseDto
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET

interface CoursesApi {

    @GET("courses")
    suspend fun getCourses(): Flow<CourseDto>

}