package com.example.mpp.mobile.breeds.presentation

import kotlinx.coroutines.*
import platform.darwin.dispatch_async
import platform.darwin.dispatch_get_main_queue
import kotlin.coroutines.CoroutineContext

actual open class BaseViewModel {
    private val coroutineContext = MainDispatcher() + SupervisorJob()
    actual val scope: CoroutineScope
        get() = CoroutineScope(coroutineContext)

    @Suppress("unused")
    protected actual open fun onCleared() {
        coroutineContext.cancelChildren()
    }

    class MainDispatcher: CoroutineDispatcher(){
        override fun dispatch(context: CoroutineContext, block: Runnable) {
            dispatch_async(dispatch_get_main_queue()){
                block.run()
            }
        }

    }
}