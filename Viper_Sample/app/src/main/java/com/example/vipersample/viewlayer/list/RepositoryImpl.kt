package com.example.vipersample.viewlayer.list

import com.example.vipersample.datalayer.MoviePage
import com.example.vipersample.datalayer.MoviesApiImpl

class RepositoryImpl : ListContract.Repository {

    // This should be injected in class constructor
    private val api = MoviesApiImpl()

    override suspend fun getMovies(): MoviePage? {
        /*
         * Different data sources could be used here.
         * For example, an in-memory cache.
         */
        return api.getMovieList()
    }
}