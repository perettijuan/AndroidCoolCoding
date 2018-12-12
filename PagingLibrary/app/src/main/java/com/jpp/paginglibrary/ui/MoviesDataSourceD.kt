package com.jpp.paginglibrary.ui

import android.os.Looper
import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.jpp.paginglibrary.datalayer.DataMovie
import com.jpp.paginglibrary.domainlayer.GetMoviePageUseCase
import com.jpp.paginglibrary.domainlayer.MoviesUseCaseResponse
import java.lang.RuntimeException

/**
 * This is where the actual movies fetching happens.
 * It seams reasonable that the UseCase is executed in here, instead
 * of being executed by the ViewModel.
 */
class MoviesDataSourceD : PageKeyedDataSource<Int, DataMovie>() {


    private val useCase by lazy { GetMoviePageUseCase() }

    val loadingWhenPages by lazy { MutableLiveData<MoviesViewState>() }

    /*
     * This method is responsible to load the data initially
     * when app screen is launched for the first time.
     * We are fetching the first page data from the api
     * and passing it via the callback method to the UI.
     */
    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, DataMovie>) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            throw RuntimeException("Executing on UI thread baby")
        }

        loadingWhenPages.postValue(MoviesViewState.Loading)

        useCase.execute(1).let {
            when (it) {
                MoviesUseCaseResponse.Error -> {
                    loadingWhenPages.postValue(MoviesViewState.Error)
                }
                is MoviesUseCaseResponse.Success -> {
                    loadingWhenPages.postValue(MoviesViewState.Loaded)
                    callback.onResult(it.moviePage.results, null, 2)
                }
            }
        }
    }

    /*
    * This method it is responsible for the subsequent call to load the data page wise.
    * This method is executed in the background thread
    * We are fetching the next page data from the api
    * and passing it via the callback method to the UI.
    * The "params.key" variable will have the updated value.
    */
    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, DataMovie>) {

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

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, DataMovie>) {
        //no-op
    }

}