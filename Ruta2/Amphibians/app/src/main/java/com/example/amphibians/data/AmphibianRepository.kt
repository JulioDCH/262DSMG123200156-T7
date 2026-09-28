package com.example.amphibians.data

class AmphibianRepository(
    private val amphibianApiService: AmphibianApiService
) {

    suspend fun getAmphibians(): List<Amphibian> {
        return amphibianApiService.getAmphibians()
    }
}