package com.example.course.di

import com.example.data.repository.CoursesRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {

    single { CoursesRepositoryImpl(get()) }

}