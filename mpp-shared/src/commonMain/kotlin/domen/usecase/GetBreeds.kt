package com.example.mpp.mobile.domen.usecase

import com.example.mpp.mobile.domen.BreedRepository
import com.example.mpp.mobile.domen.model.Breed

class GetBreeds(private val repository: BreedRepository) {
    suspend fun getBreeds(): List<Breed>{
        return repository.getBreeds()
    }
}