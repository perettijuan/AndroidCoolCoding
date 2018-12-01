package com.jpp.paginglibrary.ui

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.jpp.paginglibrary.datalayer.Movie

/**
 * Responsible for creating the DataSource instance needed and notifying
 * when it is ready.
 */
class MoviesDataSourceFactory : DataSource.Factory<Int, Movie>() {

    val mutableLiveData by lazy { MutableLiveData<MoviesDataSource>() }
    private lateinit var dataSource: MoviesDataSource

    override fun create(): DataSource<Int, Movie> {
        dataSource = MoviesDataSource()
        mutableLiveData.postValue(dataSource)
        return dataSource
    }
}