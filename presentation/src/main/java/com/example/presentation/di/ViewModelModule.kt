package com.example.presentation.di

import com.example.presentation.screens.courses.CoursesViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { CoursesViewModel(get()) }
}
