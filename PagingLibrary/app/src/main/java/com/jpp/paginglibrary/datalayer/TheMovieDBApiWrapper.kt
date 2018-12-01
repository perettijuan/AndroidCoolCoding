package com.jpp.paginglibrary.datalayer

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class TheMovieDBApiWrapper {


    companion object {
        val API: TheMovieDBApi by lazy {
            // create Retrofit instance
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            val client = OkHttpClient.Builder().addInterceptor(loggingInterceptor).build()
            val retrofit = Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl("https://api.themoviedb.org/3/")
                    .client(client)
                    .build()
            // create API instance
            retrofit.create(TheMovieDBApi::class.java)
        }
    }


    /**
     * Retrieves the provided [page] of movies currently playing on theaters.
     */
    fun getNowPlaying(page: Int): MoviePage? =
        tryCatchOrReturnNull { API.getNowPlaying(page, "eddf6980a6d7dadd72386f55f94ab571").execute().body() }


    private inline fun <T : Any> tryCatchOrReturnNull(block: () -> T?): T? {
        return try {
            block()
        } catch (ex: Exception) {
            null
        }
    }
}