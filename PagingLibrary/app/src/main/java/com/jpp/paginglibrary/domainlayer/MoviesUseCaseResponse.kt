package com.jpp.paginglibrary.domainlayer

import com.jpp.paginglibrary.datalayer.MoviePage

sealed class MoviesUseCaseResponse {
    object Error : MoviesUseCaseResponse()
    data class Success(val moviePage: MoviePage) : MoviesUseCaseResponse()
}