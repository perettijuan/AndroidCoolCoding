package com.jpp.navigationcomponents.domain

object MoviesManager {
    private val movies = mutableMapOf<Int, String>()

    fun getMovieList(): List<Movie> = movies.map { Movie(it.key, it.value) }

    fun getMovie(id: Int): Movie? = movies
            .filter { it.key == id }
            .map { Movie(it.key, it.value) }
            .firstOrNull()

    fun addMovie(title: String) {
        validateText(title)
        val nextId = getNextId()
        movies[nextId] = title
    }


    private fun getNextId(): Int = movies.count() + 1
    private fun validateText(noteText: String) {
        require(noteText.isNotBlank()) { "Note text cannot be blank" }
    }
}