package com.jpp.paginglibrary.ui

import android.os.Looper
import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.jpp.paginglibrary.datalayer.Movie
import com.jpp.paginglibrary.domainlayer.GetMoviePageUseCase
import com.jpp.paginglibrary.domainlayer.MoviesUseCaseResponse
import java.lang.RuntimeException

class MoviesDataSource : PageKeyedDataSource<Int, Movie>() {


    private val useCase by lazy { GetMoviePageUseCase() }

    val initialLoading by lazy { MutableLiveData<MoviesViewState>() }
    val loadingWhenPages by lazy { MutableLiveData<MoviesViewState>() }


    /*
     * Step 2: This method is responsible to load the data initially
     * when app screen is launched for the first time.
     * We are fetching the first page data from the api
     * and passing it via the callback method to the UI.
     */
    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Movie>) {

        if (Looper.myLooper() == Looper.getMainLooper()) {
            throw RuntimeException("Executing on UI thread baby")
        }

        initialLoading.postValue(MoviesViewState.Loading)

        useCase.execute(1).let {
            when (it) {
                MoviesUseCaseResponse.Error -> {
                    initialLoading.postValue(MoviesViewState.Error)
                }
                is MoviesUseCaseResponse.Success -> {
                    initialLoading.postValue(MoviesViewState.Loaded)
                    callback.onResult(it.moviePage.results, null, 2)
                }
            }
        }
    }

    /*
    * Step 3: This method it is responsible for the subsequent call to load the data page wise.
    * This method is executed in the background thread
    * We are fetching the next page data from the api
    * and passing it via the callback method to the UI.
    * The "params.key" variable will have the updated value.
    */
    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Movie>) {

        if (Looper.myLooper() == Looper.getMainLooper()) {
            throw RuntimeException("Executing on UI thread baby")
        }

        loadingWhenPages.postValue(MoviesViewState.Loading)

        useCase.execute(params.key).let {
            when (it) {
                MoviesUseCaseResponse.Error -> {
                    loadingWhenPages.postValue(MoviesViewState.Error)
                }
                is MoviesUseCaseResponse.Success -> {
                    loadingWhenPages.postValue(MoviesViewState.Loaded)
                    callback.onResult(it.moviePage.results, params.key + 1)
                }
            }
        }

    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Movie>) {
        //no-op
    }

}