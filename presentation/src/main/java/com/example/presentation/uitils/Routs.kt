package com.example.presentation.uitils

import kotlinx.serialization.Serializable

@Serializable
sealed class Routs {
    @Serializable
    data object AuthRout : Routs()
    @Serializable
    data object DetailCourseRout : Routs()
    @Serializable
    data object CoursesRout : Routs()
    @Serializable
    data object ProfileRout : Routs()
    @Serializable
    data object FavoritesRout : Routs()
}