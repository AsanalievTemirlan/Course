package com.example.data.network.api

import com.example.data.network.dto.CourseDto
import retrofit2.http.GET

interface CoursesApi {

    @GET("courses")
    suspend fun getCourses(): CourseDto

}