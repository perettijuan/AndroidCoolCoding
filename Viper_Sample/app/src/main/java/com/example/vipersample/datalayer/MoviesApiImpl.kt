package com.example.vipersample.datalayer

import android.util.Log
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import io.ktor.client.features.observer.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

class MoviesApiImpl : MoviesApi {

    private val httpClient = initHttpClient()
    private val jsonConfiguration: Json by lazy {
        Json {
            isLenient = true
            ignoreUnknownKeys = true
        }
    }

    override suspend fun getMovieList(): MoviePage? {
        val request = httpClient.get<HttpStatement> {
            url {
                protocol = URLProtocol.HTTPS
                method = HttpMethod.Get
                host = "api.themoviedb.org/3"
                path("movie","now_playing")
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

    private fun initHttpClient(): HttpClient {
        return HttpClient(Android) {
            followRedirects = false
            expectSuccess = false

            install(JsonFeature) {
                serializer = KotlinxSerializer()

                engine {
                    connectTimeout = TIME_OUT
                    socketTimeout = TIME_OUT
                }
            }

            install(Logging) {
                logger = object : Logger {
                    override fun log(message: String) {
                        Log.v("Logger Ktor =>", message)
                    }

                }
                level = LogLevel.ALL
            }

            install(ResponseObserver) {
                onResponse { response ->
                    Log.d("HTTP status:", "${response.status.value}")
                }
            }
        }
    }

    companion object {
        private const val TIME_OUT = 60_000
    }
}