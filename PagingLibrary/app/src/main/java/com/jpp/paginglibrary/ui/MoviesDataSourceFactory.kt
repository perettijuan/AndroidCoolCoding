package com.jpp.paginglibrary.ui

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.jpp.paginglibrary.datalayer.DataMovie

/**
 * Responsible for creating the DataSource instance needed and notifying
 * when it is ready.
 */
class MoviesDataSourceFactory : DataSource.Factory<Int, DataMovie>() {

    val mutableLiveData by lazy { MutableLiveData<MoviesDataSourceD>() }
    private lateinit var dataSource: MoviesDataSourceD

    override fun create(): DataSource<Int, DataMovie> {
        dataSource = MoviesDataSourceD()
        mutableLiveData.postValue(dataSource)
        return dataSource
    }
}