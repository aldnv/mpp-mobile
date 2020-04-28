package com.example.mpp.mobile.livedata

expect open class Observable<T> {
    open val value: T?
}