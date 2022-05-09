package com.jpp.myfirstkmm

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow

fun <T> Flow<T>.collectMe(
    onEach: (T) -> Unit,
    onCompletion: (cause: Throwable?) -> Unit
): Cancellable {
    val scope = CoroutineScope(SupervisorJob() + Dispatchers.Main)

    scope.launch {
        try {
            collect {
                onEach(it)
            }

            onCompletion(null)
        } catch (e: Throwable) {
            onCompletion(e)
        }
    }

    return object : Cancellable {
        override fun cancel() {
            scope.cancel()
        }
    }
}