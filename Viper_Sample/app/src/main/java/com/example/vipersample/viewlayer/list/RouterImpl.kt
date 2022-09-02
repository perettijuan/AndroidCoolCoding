package com.example.vipersample.viewlayer.list

import androidx.navigation.NavController
import com.example.vipersample.R

class RouterImpl : ListContract.Router {

    private var navController: NavController? = null


    override fun bind(newNavController: NavController) {
        navController = newNavController
    }

    override fun unBind() {
        navController = null
    }

    override fun openMovieDetail(item: ListContract.MovieItem) {
        navController?.navigate(R.id.movieDetailsFragment)
    }
}