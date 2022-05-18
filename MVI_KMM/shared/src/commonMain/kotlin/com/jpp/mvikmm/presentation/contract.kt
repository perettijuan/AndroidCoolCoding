package com.jpp.mvikmm.presentation

import com.badoo.reaktive.observable.Observable
import com.badoo.reaktive.observable.ObservableWrapper

/**
 * Represents the state of the View at any given time. This class is a strict representation
 * of the state of each component that is part of the view hierarchy.
 */
data class UiState(
    val loadingVisible: Boolean = false,
    val content: Content = Content()
) {
    data class Content(
        val isVisible: Boolean = false,
        val text: String = ""
    )
}

/**
 * The main responsibility of the Presenter is to transform - or reduce - the state of
 * the application (the Model) into presentation state (a.k.a. UiState).
 */
interface Presenter {
    /**
     * Notifies every change in the state of View.
     *
     * Scheduling: operation scheduled only in main application thread.
     *
     * Emits: new UiState.
     */
    val uiState: ObservableWrapper<UiState>
}

/**
 * Encapsulates all the intents the user can trigger. Each intent will produce a change
 * in the state of the application which, in time, will produce a change in the state
 * of the View.
 */
interface UserIntent {

    /**
     * Called when the user has pressed a button.
     */
    fun onButtonPressed()
}

/**
 * Represents the lifecycle of the Mvi components. Such lifecycle is always
 * dictated by the View component of the layer since it is the one that
 * defines the scope of the View hierarchy.
 * This API is particularly useful for Android applications.
 */
interface MviLifecycle {

    /**
     * Represents each event that is detected by the lifeycle of the View.
     */
    enum class Event {
        ON_VIEW_CREATED,
        ON_VIEW_READY,
        ON_VIEW_UNREADY,
        ON_VIEW_DESTROYED
    }

    val events: Observable<Event>
}


