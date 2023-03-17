package com.jpp.toprated.data

import com.jpp.core.networking.HttpClientProvider
import com.jpp.core.networking.MovieDetail
import com.jpp.core.networking.MoviePage
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

class MoviesApiImpl(private val httpClientProvider: HttpClientProvider) : MoviesApi {

    private val client by lazy { httpClientProvider.provide() }
    private val jsonConfiguration: Json by lazy {
        Json {
            isLenient = true
            ignoreUnknownKeys = true
        }
    }

    override suspend fun getMovieList(): MoviePage? {
        val request = client.get<HttpStatement> {
            url {
                protocol = URLProtocol.HTTPS
                method = HttpMethod.Get
                host = "api.themoviedb.org/3"
                path("movie", "top_rated")
                parameters.append("page", "1")
                parameters.append("api_key", "eddf6980a6d7dadd72386f55f94ab571")
            }
        }
        val json = request.execute().readText()
        return try {
            jsonConfiguration.decodeFromString(json)
        } catch (ex: Exception) {
            null
        }
    }

    override suspend fun getMovieDetails(movieId: Double): MovieDetail? {
        val request = client.get<HttpStatement> {
            url {
                protocol = URLProtocol.HTTPS
                method = HttpMethod.Get
                host = "api.themoviedb.org/3"
                path("movie", movieId.toString())
                parameters.append("api_key", "eddf6980a6d7dadd72386f55f94ab571")
            }
        }
        val json = request.execute().readText()
        return try {
            jsonConfiguration.decodeFromString(json)
        } catch (ex: Exception) {
            null
        }
    }
}