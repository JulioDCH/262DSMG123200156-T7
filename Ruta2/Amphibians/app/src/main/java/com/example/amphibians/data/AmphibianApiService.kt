package com.example.amphibians.data

import retrofit2.http.GET

interface AmphibianApiService {

    @GET("amphibians")
    suspend fun getAmphibians(): List<Amphibian>
}