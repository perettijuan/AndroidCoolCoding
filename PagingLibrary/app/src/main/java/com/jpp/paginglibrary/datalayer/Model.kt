package com.jpp.paginglibrary.datalayer

/**
 * Represents a Movie retrieved from the backend.
 */
data class Movie(val id: Double,
                 val title: String,
                 val original_title: String,
                 val overview: String,
                 val release_date: String,
                 val original_language: String,
                 val poster_path: String?,
                 val backdrop_path: String?,
                 val vote_count: Double,
                 val vote_average: Float,
                 val popularity: Float)

/**
 * Represents a page of Movies retrieved from the backend.
 */
data class MoviePage(val page: Int,
                     val results: List<Movie>,
                     val total_pages: Int,
                     val total_results: Int)