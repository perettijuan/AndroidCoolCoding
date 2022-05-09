package com.jpp.myfirstkmm.api

import com.jpp.myfirstkmm.Cancellable
import com.jpp.myfirstkmm.collectMe
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

interface Api {

    sealed class Result(val message: String) {
        data class Loading(val m: String) : Result(m)
        data class Success(val m: String) : Result(m)
        data class Failure(val m: String) : Result(m)
    }

    val state: Flow<Result>
    suspend fun flowMe(count: Int, succeed: Boolean)
    fun tasks(onEach: (Result) -> Unit, onCompletion: (Throwable?) -> Unit): Cancellable =
        state.collectMe(onEach, onCompletion)
}


class ApiImpl : Api {
    private val _state = MutableStateFlow<Api.Result>(Api.Result.Loading("Starting"))
    override val state: Flow<Api.Result> = _state

    private val httpClient = HttpClient()
    private var page = 0
    private var currentCount = 0

    private suspend fun executeApi(succeed: Boolean, page: Int) {
        val url = if (succeed) {
            BASE_URL + page
        } else {
            "$BASE_URL-1"
        }
        val response = httpClient.get(url)
        val pageResult = response.bodyAsText()
        _state.emit(Api.Result.Success(pageResult))
        kotlinx.coroutines.delay(1000)
    }

    override suspend fun flowMe(count: Int, succeed: Boolean) {
        while (count > currentCount) {
            page += 1
            kotlinx.coroutines.delay(1000)
            _state.emit(Api.Result.Loading("Loading...$page"))
            executeApi(true, page)
            currentCount += 1
        }
        if (!succeed) {
            error("OOps")
        }
    }


    private companion object {
        private const val BASE_URL =
            "https://api.themoviedb.org/3/movie/now_playing?api_key=eddf6980a6d7dadd72386f55f94ab571&language=en&page="
    }
}