package com.jpp.paginglibrary.domainlayer

import android.os.Looper
import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.jpp.paginglibrary.datalayer.MoviesRepository
import java.lang.RuntimeException

class MoviesDataSource : PageKeyedDataSource<Int, DomainMovie>() {

    val dataSourceState by lazy { MutableLiveData<MoviesDataSourceState>() }
    private val repository by lazy { MoviesRepository() }


    /*
     * This method is responsible to load the data initially
     * when app screen is launched for the first time.
     * We are fetching the first page data from the api
     * and passing it via the callback method to the UI.
     */
    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, DomainMovie>) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            throw RuntimeException("Executing on UI thread baby")
        }

        dataSourceState.postValue(MoviesDataSourceState.LoadingInitial)
        fetchMoviePage(1) {
            it?.let { movies ->
                dataSourceState.postValue(MoviesDataSourceState.LoadingInitialDone)
                callback.onResult(movies, null, 2)
            } ?: run {
                dataSourceState.postValue(MoviesDataSourceState.Error)
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
    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, DomainMovie>) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            throw RuntimeException("Executing on UI thread baby")
        }

        dataSourceState.postValue(MoviesDataSourceState.LoadingAfter)
        fetchMoviePage(params.key) {
            it?.let { movies ->
                dataSourceState.postValue(MoviesDataSourceState.LoadingAfterDone)
                callback.onResult(movies, params.key + 1)
            } ?: run {
                dataSourceState.postValue(MoviesDataSourceState.Error)
            }
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, DomainMovie>) {
        //no-op
    }

    /**
     * Fetches the movie page indicated by [page] and maps the result to a [DomainMovie]
     */
    private fun fetchMoviePage(page: Int, callback: (List<DomainMovie>?) -> Unit) {
        repository.getNowPlayingMoviePage(page)?.let {
            dataSourceState.postValue(MoviesDataSourceState.LoadingInitialDone)
            callback.invoke(it.results.map { dataMovie ->
                DomainMovie(
                        id = dataMovie.id,
                        title = dataMovie.title,
                        originalTitle = dataMovie.original_title,
                        overview = dataMovie.overview,
                        releaseDate = dataMovie.release_date,
                        originalLanguage = dataMovie.original_language,
                        posterPath = dataMovie.poster_path,
                        backdropPath = dataMovie.backdrop_path,
                        voteCount = dataMovie.vote_count,
                        voteAverage = dataMovie.vote_average,
                        popularity = dataMovie.popularity

                )
            })
        } ?: run {
            callback.invoke(null) // this should be something more representative, but for simplicity is enough
        }
    }
}