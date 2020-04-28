package com.example.mpp.mobile.breeds.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mpp.mobile.domen.usecase.GetBreeds
import com.example.mpp.mobile.navigator.Navigator

class BreedViewModelFactory(private val getBreeds: GetBreeds, private val navigator: Navigator): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        return BreedViewModel(getBreeds, navigator) as T
    }
}