package com.jpp.mvikmm.model

import com.badoo.reaktive.observable.Observable

/**
 * Repository definition that handles the state of a message.
 */
interface MessageRepository {

    sealed class State {
        object Loading : State()
        data class Loaded(val message: Message) : State()
        data class FailedToLoad(val message: String) : State()
    }

    val messagesState: Observable<State>

    fun produceMessage()
}