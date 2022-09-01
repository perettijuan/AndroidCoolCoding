package com.example.vipersample.datalayer

interface MoviesApi {

    suspend fun getMovieList(): MoviePage?

}