package com.example.mpp.mobile.data.source

import com.example.mpp.mobile.domen.model.Breed

import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration

class BreedsDataSource(private val breedsApi: BreedsApi) {

    suspend fun getBreeds(): List<Breed> {
        val jsonParser = Json(JsonConfiguration.Stable.copy(encodeDefaults = true, ignoreUnknownKeys = true))
        val json = breedsApi.getBreeds()
        val breedData: BreedData = jsonParser.parse(BreedData.serializer(), json)
        return breedData.message.map { Breed(it) }
    }
}