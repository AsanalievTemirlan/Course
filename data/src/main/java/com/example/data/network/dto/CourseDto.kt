package com.example.data.network.dto


import com.example.domain.model.CoursesModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CourseDto(
    @SerialName("courses")
    val courses: List<Course>
)
fun CourseDto.toDomain(): CoursesModel = CoursesModel(
    cours = courses.map { it.toDomain() }
)