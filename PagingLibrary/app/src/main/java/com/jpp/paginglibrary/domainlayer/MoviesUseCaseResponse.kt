package com.jpp.paginglibrary.domainlayer

import com.jpp.paginglibrary.datalayer.DataMoviePage

sealed class MoviesUseCaseResponse {
    object Error : MoviesUseCaseResponse()
    data class Success(val moviePage: DataMoviePage) : MoviesUseCaseResponse()
}