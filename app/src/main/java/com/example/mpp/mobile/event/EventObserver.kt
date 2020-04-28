package com.example.mpp.mobile.event

import androidx.lifecycle.Observer

class EventObserver<T>(private val block: (T) -> Unit): Observer<Event<T>> {
    override fun onChanged(t: Event<T>) {
        t.value?.apply {
            block(this)
        }
    }
}