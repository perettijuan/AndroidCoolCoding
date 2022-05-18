package com.jpp.mvikmm.presentation

import com.badoo.reaktive.observable.Observable

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
    val uiState: Observable<UiState>

    /**
     * Called when the View is ready to start rendering [UiState].
     *
     * TODO This is breaking unidirectional data flow
     */
    fun onReady()

    /**
     * Called when the View is unable to rendering [UiState].
     *
     * TODO This is breaking unidirectional data flow
     */
    fun onUnready()
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


