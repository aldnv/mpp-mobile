package com.example.mpp.mobile.livedata

actual open class Observable<T> {

    private var mValue: T? = null

    private val observers = mutableListOf<(T) -> Unit>()

    actual open val value: T?
        get() = mValue

    @Suppress("unused")
    fun observe(
        block: (T) -> Unit
    ) {
        observers.add(block)
        value?.run {
            block(this)
        }
    }

    fun onChange(data: T){
        mValue = data
        notify()
    }

    private fun notify(){
        value?.let{data ->
            observers.forEach { block -> block(data) }
        }
    }

}