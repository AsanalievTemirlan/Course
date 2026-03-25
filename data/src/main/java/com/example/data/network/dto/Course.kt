package com.example.data.network.dto

import com.example.domain.model.CourseModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Course(
    @SerialName("hasLike")
    val hasLike: Boolean,
    @SerialName("id")
    val id: Int,
    @SerialName("price")
    val price: String,
    @SerialName("publishDate")
    val publishDate: String,
    @SerialName("rate")
    val rate: String,
    @SerialName("startDate")
    val startDate: String,
    @SerialName("text")
    val text: String,
    @SerialName("title")
    val title: String
)

fun Course.toDomain(): CourseModel {
    return CourseModel(
        hasLike = hasLike,
        id = id,
        price = price,
        publishDate = publishDate,
        rate = rate,
        startDate = startDate,
        text = text,
        title = title
    )
}
