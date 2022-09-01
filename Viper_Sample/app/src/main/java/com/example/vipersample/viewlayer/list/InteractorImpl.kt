package com.example.vipersample.viewlayer.list

import com.example.vipersample.datalayer.MoviePage
import com.example.vipersample.datalayer.MoviesApiImpl
import com.example.vipersample.util.Try

class InteractorImpl : ListContract.Interactor {

    // This should be injected in class constructor
    private val api = MoviesApiImpl()

    override suspend fun getMovies(): Try<MoviePage> {
        val movieList = api.getMovieList()
        return if (movieList != null) {
            Try.Success(movieList)
        } else {
            Try.Failure(Try.FailureCause.Unknown)
        }
    }
}