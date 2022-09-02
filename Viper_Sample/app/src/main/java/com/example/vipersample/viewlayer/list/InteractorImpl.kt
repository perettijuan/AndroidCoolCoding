package com.example.vipersample.viewlayer.list

import com.example.vipersample.datalayer.MoviePage
import com.example.vipersample.datalayer.MoviesApiImpl
import com.example.vipersample.util.Try

class InteractorImpl(private val repository: ListContract.Repository) : ListContract.Interactor {

    override suspend fun getMovies(): Try<MoviePage> {
        val movieList = repository.getMovies()
        return if (movieList != null) {
            Try.Success(movieList)
        } else {
            Try.Failure(Try.FailureCause.Unknown)
        }
    }
}