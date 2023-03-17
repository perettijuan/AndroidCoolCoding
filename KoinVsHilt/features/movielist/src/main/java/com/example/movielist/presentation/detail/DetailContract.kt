package com.example.movielist.presentation.detail

import androidx.navigation.NavController
import com.jpp.core.networking.MovieDetail
import com.jpp.core.utils.Try

interface DetailContract {

    interface View {
        fun showLoading()
        fun hideLoading()
        fun showDetail(detail: MovieDetailUi)
        fun showErrorMessage(message: String)
    }

    interface Presenter {
        fun bindView(view: View)
        fun onViewCreated(id: Double)
        fun unBindView()
    }

    interface Interactor {
        suspend fun getMovies(id: Double): Try<MovieDetail>
    }

    interface Router {
        fun bind(newNavController: NavController)
        fun unBind()
    }

    data class MovieDetailUi(
        val id: Double,
        val title: String,
        val overview: String
    )
}