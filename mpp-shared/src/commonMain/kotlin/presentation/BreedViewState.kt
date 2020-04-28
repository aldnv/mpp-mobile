package com.example.mpp.mobile.breeds.presentation

import com.example.mpp.mobile.domen.model.Breed

sealed class BreedViewState {
    object Loading: BreedViewState()
    object Error: BreedViewState()
    data class Success(val value: List<Breed>): BreedViewState()
}