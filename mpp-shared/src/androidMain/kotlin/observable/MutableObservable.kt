package com.example.mpp.mobile.livedata

actual open class MutableObservable<T> actual constructor(): Observable<T>() {
    actual override var value: T?
        get() = liveData.value
        set(value) {
            liveData.postValue(value)
        }

    actual constructor(value: T): this(){
        liveData.value = value
    }

}