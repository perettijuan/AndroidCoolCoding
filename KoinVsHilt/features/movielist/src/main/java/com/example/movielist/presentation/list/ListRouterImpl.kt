package com.example.movielist.presentation.list

import androidx.navigation.NavController
import com.example.movielist.R
import com.example.movielist.presentation.detail.DetailParam

// Fragment scoped
internal class ListRouterImpl : ListContract.Router {

    private var navController: NavController? = null


    override fun bind(newNavController: NavController) {
        navController = newNavController
    }

    override fun unBind() {
        navController = null
    }

    override fun openMovieDetail(item: ListContract.MovieItem) {
        DetailParam.selectedMovieId = item.id
        navController?.navigate(R.id.detailFragment)
    }
}