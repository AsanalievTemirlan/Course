package com.example.data.di

import com.example.data.network.api.CoursesApi
import com.example.data.network.interceptor.MockInterceptor
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory

val networkModule = module {

    single {
        Json {
            ignoreUnknownKeys = true
        }
    }

    single {
        OkHttpClient.Builder()
            .addInterceptor(MockInterceptor(get()))
            .build()
    }

    single {
        Retrofit.Builder()
            .baseUrl("https://mock.api/")
            .client(get())
            .addConverterFactory(
                get<Json>().asConverterFactory("application/json".toMediaType())
            )
            .build()
    }

    single { get<Retrofit>().create(CoursesApi::class.java) }
}
