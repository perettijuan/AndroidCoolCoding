package com.example.movielist.list.domain

import com.example.movielist.list.data.MoviesApi
import com.jpp.core.networking.MoviePage

// Singleton
interface MoviesRepository {
    suspend fun getMovies(): MoviePage?
}

class MoviesRepositoryImpl(private val api: MoviesApi) : MoviesRepository {

    override suspend fun getMovies(): MoviePage? {
        /*
         * Different data sources could be used here.
         * For example, an in-memory cache.
         */
        return api.getMovieList()
    }
}