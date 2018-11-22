package com.jpp.architecturecomponents.ui

/**
 * A state reducer combines two states provided into a single one. The idea is that we always
 * produce a unique, deterministic state for each interaction.
 * Source -> http://hannesdorfmann.com/android/mosby3-mvi-3
 */
class MainActivityViewStateReducer {

    /**
     * Reduces the [previousState] by appending new information from the [changes] provided.
     */
    operator fun invoke(previousState: MainActivityViewState?, changes: MainActivityViewState): MainActivityViewState {
        return when (changes) {
            MainActivityViewState.Loading -> MainActivityViewState.Loading
            MainActivityViewState.Error -> MainActivityViewState.Error
            /*
             * If previousState already had items, produce a new state with the new items minus the
             * previous items.
             */
            is MainActivityViewState.Items -> {
                if (previousState is MainActivityViewState.Items) {
                    MainActivityViewState.Items(changes.items.toMutableList().minus(previousState.items))
                } else {
                    MainActivityViewState.Items(changes.items)
                }
            }
        }
    }
}