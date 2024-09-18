package com.example.novelsbookshelf.repository

import com.example.novelsbookshelf.network.NovelsApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

interface AppContainer {
    val novelsRepository: NovelsRepository
}

object AppContainerImpl: AppContainer {
    private const val BASE_URL = "https://www.googleapis.com/"

    private val json = Json {
        ignoreUnknownKeys = true
    }

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .build()

    private val retrofitService: NovelsApiService by lazy {
        retrofit.create(NovelsApiService::class.java)
    }

    override val novelsRepository: NovelsRepository by lazy {
        NovelsRepositoryImpl(retrofitService)
    }
}