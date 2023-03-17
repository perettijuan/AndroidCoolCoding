package com.example.movielist.list.data

import com.jpp.core.networking.MoviePage

interface MoviesApi {
    suspend fun getMovieList(): MoviePage?
}