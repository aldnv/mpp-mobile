package com.example.mpp.mobile.livedata

actual class MutableObservable<T> actual constructor() :
    Observable<T>() {
    actual override var value: T?
        get() = super.value
        set(value) {
            if (value!=null) {
                onChange(value)
            }
        }

    @Suppress("unused")
    actual constructor(value: T): this() {
        onChange(value)
    }

}