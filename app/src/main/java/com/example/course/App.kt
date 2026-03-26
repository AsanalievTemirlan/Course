package com.example.course

import android.app.Application
import com.example.course.di.repositoryModule
import com.example.course.di.useCaseModule
import com.example.data.di.networkModule
import com.example.presentation.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(networkModule, repositoryModule, useCaseModule, viewModelModule)
        }
    }

}