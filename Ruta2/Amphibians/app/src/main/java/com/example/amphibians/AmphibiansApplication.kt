package com.example.amphibians

import android.app.Application
import com.example.amphibians.data.AmphibianApiService
import com.example.amphibians.data.AmphibianRepository
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory

class AmphibiansApplication : Application() {

    val container = AppContainer()
}

class AppContainer {

    private val baseUrl =
        "https://android-kotlin-fun-mars-server.appspot.com/"

    private val json = Json {
        ignoreUnknownKeys = true
    }

    private val retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(
            json.asConverterFactory("application/json".toMediaType())
        )
        .build()

    private val amphibianApiService =
        retrofit.create(AmphibianApiService::class.java)

    val amphibianRepository =
        AmphibianRepository(amphibianApiService)
}