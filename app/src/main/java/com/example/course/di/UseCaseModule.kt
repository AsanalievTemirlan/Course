package com.example.course.di

import com.example.domain.usecases.CoursesUseCase
import org.koin.dsl.module

val useCaseModule = module {

    single { CoursesUseCase(get()) }

}