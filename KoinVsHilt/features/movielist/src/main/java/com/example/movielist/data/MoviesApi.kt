package com.example.movielist.data

import com.jpp.core.networking.MovieDetail
import com.jpp.core.networking.MoviePage

internal interface MoviesApi {
    suspend fun getMovieList(): MoviePage?
    suspend fun getMovieDetails(movieId: Double): MovieDetail?
}