package com.jpp.paginglibrary.datalayer

/**
 * This should be an interface and the dependencies should be provided using
 * a DI framework (like Dagger). For simplicity, we create the instances needed and
 * declare this as a class.
 */
class MoviesRepository(private val apiWrapper: TheMovieDBApiWrapper = TheMovieDBApiWrapper()) {
    fun getNowPlayingMoviePage(page: Int): DataMoviePage?  = apiWrapper.getNowPlaying(page)
}