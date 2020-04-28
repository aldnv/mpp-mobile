package com.example.mpp.mobile.domen

import com.example.mpp.mobile.domen.model.Breed

interface BreedRepository {
    suspend fun getBreeds(): List<Breed>
}