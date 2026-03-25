package com.example.course

import android.app.Application
import com.example.course.di.repositoryModule
import com.example.course.di.useCaseModule
import com.example.data.di.networkModule
import org.koin.core.context.startKoin

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(networkModule, repositoryModule, useCaseModule)
        }
    }

}