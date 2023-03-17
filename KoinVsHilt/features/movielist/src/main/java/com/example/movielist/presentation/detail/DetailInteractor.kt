package com.example.movielist.presentation.detail

import com.example.movielist.domain.MoviesRepository
import com.jpp.core.networking.MovieDetail
import com.jpp.core.utils.Try

// Fragment scoped
class DetailInteractor(private val repository: MoviesRepository) : DetailContract.Interactor {

    override suspend fun getMovies(id: Double): Try<MovieDetail> {
        val detail = repository.getMovieDetail(id)
        return if (detail != null) {
            Try.Success(detail)
        } else {
            Try.Failure(Try.FailureCause.Unknown)
        }
    }
}