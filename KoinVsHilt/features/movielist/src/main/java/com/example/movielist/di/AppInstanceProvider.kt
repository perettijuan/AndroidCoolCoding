package com.example.movielist.di

import com.example.movielist.list.data.MoviesApi
import com.example.movielist.list.data.MoviesApiImpl
import com.example.movielist.list.domain.MoviesRepository
import com.example.movielist.list.domain.MoviesRepositoryImpl
import com.jpp.core.networking.HttpClientProvider
import com.jpp.core.networking.HttpClientProviderImpl

object AppInstanceProvider {
    private val httpClientProvider: HttpClientProvider = HttpClientProviderImpl()
    private val movieApi: MoviesApi = MoviesApiImpl(httpClientProvider)
    private val moviesRepository: MoviesRepository = MoviesRepositoryImpl(movieApi)

    fun getMoviesRepository(): MoviesRepository = moviesRepository
}