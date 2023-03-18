package com.example.movielist.presentation.detail

import com.example.movielist.domain.MoviesRepository
import com.example.movielist.domain.NetworkRepository
import com.jpp.core.networking.MovieDetail
import com.jpp.core.utils.Try

// Fragment scoped
internal class DetailInteractor(
    private val repository: MoviesRepository,
    private val networkRepository: NetworkRepository
) : DetailContract.Interactor {

    override suspend fun getMovies(id: Double): Try<MovieDetail> {
        if (networkRepository.getConnected().not()) {
            return Try.Failure(Try.FailureCause.NotConnected)
        }

        val detail = repository.getMovieDetail(id)
        return if (detail != null) {
            Try.Success(detail)
        } else {
            Try.Failure(Try.FailureCause.Unknown)
        }
    }
}