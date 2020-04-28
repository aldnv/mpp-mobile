package com.example.mpp.mobile.breeds.presentation

import com.example.mpp.mobile.domen.model.Breed
import com.example.mpp.mobile.domen.usecase.GetBreeds
import com.example.mpp.mobile.livedata.MutableObservable
import com.example.mpp.mobile.livedata.Observable
import com.example.mpp.mobile.navigator.Navigator
import kotlinx.coroutines.launch

class BreedViewModel(private val getBreeds: GetBreeds, private val navigator: Navigator
): BaseViewModel() {

    private val mutableData = MutableObservable<BreedViewState>()

    val data: Observable<BreedViewState> = mutableData

    init {
        load()
    }

    fun load(){
        mutableData.value = BreedViewState.Loading
        scope.launch {
            try {
                mutableData.value = BreedViewState.Success(getBreeds.getBreeds())
            }catch (e: Exception){
                mutableData.value = BreedViewState.Error
            }
        }
    }

    fun onBreedClick(breed: Breed){
        navigator.openImageList(breed)
    }
}