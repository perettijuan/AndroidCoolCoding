package com.example.movielist.data

import com.jpp.core.networking.MoviePage

interface MoviesApi {
    suspend fun getMovieList(): MoviePage?
}