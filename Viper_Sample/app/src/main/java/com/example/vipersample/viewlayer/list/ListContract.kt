package com.example.vipersample.viewlayer.list

import com.example.vipersample.datalayer.Movie
import com.example.vipersample.datalayer.MoviePage
import com.example.vipersample.util.Try

interface ListContract {
    interface View {
        fun showLoading()
        fun hideLoading()
        fun showMovies(movieList: List<MovieItem>)
        fun showErrorMessage(message: String)
    }

    interface Presenter {
        fun bindView(view: View)
        fun unBindView()
        fun onViewCreated()
        fun onMovieSelected(item: MovieItem)
    }

    interface Interactor {
        suspend fun getMovies(): Try<MoviePage>
    }

    interface Router {
        fun openMovieDetail(item: MovieItem)
    }

    data class MovieItem(
        val id: Double,
        val name: String
    )
}