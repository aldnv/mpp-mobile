package com.example.mpp.mobile.livedata

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.Observer

actual open class Observable<T> {

    protected val liveData = MediatorLiveData<T>()

    actual open val value: T?
        get() = liveData.value

    fun observe(
        lifecycleOwner: LifecycleOwner,
        block: (T) -> Unit
    ) {
        liveData.observe(lifecycleOwner, Observer(block))
    }

}