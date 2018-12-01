package com.jpp.paginglibrary.ui

/**
 * Represents the state of the movies view
 */
sealed class MoviesViewState {
    object Loading : MoviesViewState()
    object Error : MoviesViewState()
    object Loaded: MoviesViewState()
}