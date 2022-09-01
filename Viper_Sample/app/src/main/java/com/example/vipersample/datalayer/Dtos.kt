package com.example.vipersample.datalayer

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Movie(
    @SerialName("id")
    val id: Double,
    @SerialName("title")
    val title: String,
    @SerialName("original_title")
    val original_title: String,
    @SerialName("overview")
    val overview: String,
    @SerialName("release_date")
    val release_date: String,
    @SerialName("original_language")
    val original_language: String,
    @SerialName("vote_count")
    val vote_count: Double,
    @SerialName("vote_average")
    val vote_average: Float,
    @SerialName("popularity")
    val popularity: Float
)

@Serializable
data class MoviePage(
    @SerialName("page")
    val page: Int,
    @SerialName("results")
    val results: List<Movie>,
    @SerialName("total_pages")
    val total_pages: Int,
    @SerialName("total_results")
    val total_results: Int
)