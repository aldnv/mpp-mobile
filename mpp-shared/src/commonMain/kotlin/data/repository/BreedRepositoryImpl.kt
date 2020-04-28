package com.example.mpp.mobile.data.repository

import com.example.mpp.mobile.data.source.BreedsDataSource
import com.example.mpp.mobile.domen.BreedRepository
import com.example.mpp.mobile.domen.model.Breed

class BreedRepositoryImpl(private val breedsDataSource: BreedsDataSource): BreedRepository {
    override suspend fun getBreeds(): List<Breed> {
        return breedsDataSource.getBreeds()
    }
}