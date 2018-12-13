package com.jpp.paginglibrary.domainlayer

/**
 * Represents the state of the MoviesDataSource.
 */
sealed class MoviesDataSourceState {
    object LoadingInitial : MoviesDataSourceState()
    object LoadingInitialDone : MoviesDataSourceState()
    object Error : MoviesDataSourceState()
    object LoadingAfter : MoviesDataSourceState()
    object LoadingAfterDone : MoviesDataSourceState()
}