package com.jpp.myfirstkmm.api

import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*

class Api {

    private val httpClient = HttpClient()

    suspend fun executeApi(): String {
        val response = httpClient.get(BASE_URL)
        return response.bodyAsText()
    }


    private companion object {
        private const val BASE_URL =
            "https://api.themoviedb.org/3/movie/now_playing?page=1&api_key=eddf6980a6d7dadd72386f55f94ab571&language=en"
    }
}