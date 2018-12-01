package com.jpp.paginglibrary.ui

import androidx.paging.PageKeyedDataSource
import com.jpp.paginglibrary.datalayer.Movie

class MoviesDataSource : PageKeyedDataSource<Int, Movie>() {




      /*
       * Step 2: This method is responsible to load the data initially
       * when app screen is launched for the first time.
       * We are fetching the first page data from the api
       * and passing it via the callback method to the UI.
       */
    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Movie>) {




    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Movie>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Movie>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}