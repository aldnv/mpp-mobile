package com.example.mpp.mobile.livedata

expect class MutableObservable<T> ():
    Observable<T> {
    override var value: T?

    constructor(value: T)
}