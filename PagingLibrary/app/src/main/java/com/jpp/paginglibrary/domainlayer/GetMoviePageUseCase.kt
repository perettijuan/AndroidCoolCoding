package com.jpp.paginglibrary.domainlayer

import com.jpp.paginglibrary.datalayer.MoviesRepository

/**
 * This should be an interface and the dependencies should be provided using
 * a DI framework (like Dagger). For simplicity, we create the instances needed and
 * declare this as a class.
 */
class GetMoviePageUseCase(private val repository: MoviesRepository = MoviesRepository()) {


    fun execute(page: Int): MoviesUseCaseResponse? {
        return repository.getNowPlayingMoviePage(page)?.let {
            MoviesUseCaseResponse.Success(it)
        } ?: MoviesUseCaseResponse.Error
    }

}