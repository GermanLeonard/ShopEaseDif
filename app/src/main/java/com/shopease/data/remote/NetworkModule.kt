package com.shopease.data.remote

import kotlinx.serialization.json.Json
import retrofit2.Retrofit
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.ExperimentalSerializationApi
import okhttp3.MediaType.Companion.toMediaType

object NetworkModule {

    @OptIn(ExperimentalSerializationApi::class)
    fun provideApiService(): ApiService {
        val contentType = "application/json".toMediaType()

        val json = Json {
            ignoreUnknownKeys = true
        }

        val retrofit = Retrofit.Builder()
            .baseUrl("https://fakestoreapi.com/")
            .addConverterFactory(json.asConverterFactory(contentType))
            .build()

        return retrofit.create(ApiService::class.java)
    }
}
