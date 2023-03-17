package com.example.movielist.list.presentation

import androidx.navigation.NavController
import com.jpp.core.networking.MoviePage
import com.jpp.core.utils.Try

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
        fun bind(newNavController: NavController)
        fun unBind()
        fun openMovieDetail(item: MovieItem)
    }

    data class MovieItem(
        val id: Double,
        val name: String
    )
}