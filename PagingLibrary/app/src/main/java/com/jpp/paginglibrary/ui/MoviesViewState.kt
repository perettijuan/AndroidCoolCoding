package com.jpp.paginglibrary.ui

sealed class MoviesViewState {
    object Loading : MoviesViewState()
    object Error : MoviesViewState()
    object Loaded: MoviesViewState()
}