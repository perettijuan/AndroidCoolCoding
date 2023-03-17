package com.example.movielist.presentation

import androidx.navigation.NavController

class RouterImpl : ListContract.Router {

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