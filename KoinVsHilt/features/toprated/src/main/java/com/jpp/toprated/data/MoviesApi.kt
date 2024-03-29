package com.jpp.toprated.data

import com.jpp.core.networking.MovieDetail
import com.jpp.core.networking.MoviePage

interface MoviesApi {
    suspend fun getMovieList(): MoviePage?
    suspend fun getMovieDetails(movieId: Double): MovieDetail?
}