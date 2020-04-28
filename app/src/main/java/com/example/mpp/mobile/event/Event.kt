package com.example.mpp.mobile.event

class Event<T> private constructor(value: T?, private val isSingleEvent: Boolean) {

    private val mValue = value
    private var isConsumed = false

    val value: T?
    get() {
        if (isSingleEvent){
            if (isConsumed){
                return null
            }
            isConsumed = true
        }
        return mValue
    }

    companion object{
        fun <T> single(value: T): Event<T> {
            return Event(value, true)
        }

        fun <T> event(value: T): Event<T> {
            return Event(value, false)
        }
    }

}