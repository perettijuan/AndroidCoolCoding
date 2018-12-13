package com.jpp.paginglibrary.domainlayer

/**
 * Represents a Movie in the domain module
 */
data class DomainMovie(val id: Double,
                       val title: String,
                       val originalTitle: String,
                       val overview: String,
                       val releaseDate: String,
                       val originalLanguage: String,
                       val posterPath: String?,
                       val backdropPath: String?,
                       val voteCount: Double,
                       val voteAverage: Float,
                       val popularity: Float)