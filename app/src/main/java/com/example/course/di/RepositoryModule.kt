package com.example.course.di

import com.example.data.repository.CoursesRepositoryImpl
import com.example.domain.repository.CoursesRepository
import org.koin.dsl.module

val repositoryModule = module {

    single<CoursesRepository> { CoursesRepositoryImpl(get()) }

}