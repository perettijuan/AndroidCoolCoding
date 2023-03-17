package com.example.movielist.presentation.list

import com.example.movielist.domain.MoviesRepository
import com.jpp.core.networking.MoviePage
import com.jpp.core.utils.Try

// Fragment scoped
class ListInteractorImpl(private val repository: MoviesRepository) : ListContract.Interactor {

    override suspend fun getMovies(): Try<MoviePage> {
        val movieList = repository.getMovies()
        return if (movieList != null) {
            Try.Success(movieList)
        } else {
            Try.Failure(Try.FailureCause.Unknown)
        }
    }
}