package com.jpp.myfirstkmm.api

import com.jpp.myfirstkmm.CommonFlow
import com.jpp.myfirstkmm.asCommonFlow
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import kotlinx.coroutines.flow.*

class Api {

    sealed class Result(val message: String) {
        data class Loading(val m: String) : Result(m)
        data class Success(val m: String) : Result(m)
        data class Failure(val m: String) : Result(m)
    }

    private val flow = MutableStateFlow<Result>(Result.Loading("Starting"))
    val state: CommonFlow<Result> = flow.asCommonFlow()
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
        flow.emit(Result.Success(pageResult))
        kotlinx.coroutines.delay(1000)
    }

    suspend fun flowMe(count: Int, succeed: Boolean) {
        while (count > currentCount) {
            page += 1
            kotlinx.coroutines.delay(1000)
            flow.emit(Result.Loading("Loading...$page"))
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