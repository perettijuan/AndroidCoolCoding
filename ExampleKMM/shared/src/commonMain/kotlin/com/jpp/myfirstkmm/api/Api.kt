package com.jpp.myfirstkmm.api

import com.badoo.reaktive.coroutinesinterop.singleFromCoroutine
import com.badoo.reaktive.scheduler.ioScheduler
import com.badoo.reaktive.scheduler.mainScheduler
import com.badoo.reaktive.single.*
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*

interface Api {
    sealed class Result(val message: String) {
        data class Loading(val m: String) : Result(m)
        data class Success(val m: String) : Result(m)
        data class Failure(val m: String) : Result(m)
    }

    fun flowMe(count: Int, succeed: Boolean): SingleWrapper<Result>
}

interface Logger {
    fun logThread(message: String)
}

class ApiImpl(private val logger: Logger) : Api {

    private val httpClient = HttpClient()

    private var page = 0
    private var currentCount = 0

    private suspend fun executeApi(succeed: Boolean, page: Int): Api.Result {
        val url = if (succeed) {
            BASE_URL + page
        } else {
            "$BASE_URL-1"
        }
        val response = httpClient.get(url)
        val pageResult = response.bodyAsText()
        kotlinx.coroutines.delay(1000)
        return Api.Result.Success(pageResult)
    }

    private fun mockMe(): Api.Result {
        return Api.Result.Success("Ktor sucks")
    }

    override fun flowMe(count: Int, succeed: Boolean): SingleWrapper<Api.Result> {
        return singleFromFunction {
            logger.logThread("executing mock me")
            mockMe()
        }.subscribeOn(ioScheduler) // Switching to a background thread is necessary
            .observeOn(mainScheduler)
            .wrap()
    }

    private companion object {
        private const val BASE_URL =
            "https://api.themoviedb.org/3/movie/now_playing?page=1&api_key=eddf6980a6d7dadd72386f55f94ab571&language=en"
    }
}