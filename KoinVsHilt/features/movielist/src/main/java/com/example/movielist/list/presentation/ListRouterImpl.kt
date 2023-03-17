package com.example.movielist.list.presentation

import androidx.navigation.NavController

// Fragment scoped
class ListRouterImpl : ListContract.Router {

    private var navController: NavController? = null


    override fun bind(newNavController: NavController) {
        navController = newNavController
    }

    override fun unBind() {
        navController = null
    }

    override fun openMovieDetail(item: ListContract.MovieItem) {
        // TODO JPP
        // navController?.navigate(R.id.movieDetailsFragment)
    }
}