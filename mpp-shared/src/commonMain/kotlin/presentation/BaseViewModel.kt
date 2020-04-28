package com.example.mpp.mobile.breeds.presentation

import kotlinx.coroutines.CoroutineScope

expect open class BaseViewModel() {
    val scope: CoroutineScope
    protected open fun onCleared()
}