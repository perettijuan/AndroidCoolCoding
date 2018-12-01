package com.jpp.paginglibrary.datalayer

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface TheMovieDBApi {

    @GET("movie/now_playing")
    fun getNowPlaying(@Query("page") page: Int,
                      @Query("api_key") api_key: String,
                      @Query("language") language: String? = null,
                      @Query("region") region: String? = null): Call<MoviePage>
}