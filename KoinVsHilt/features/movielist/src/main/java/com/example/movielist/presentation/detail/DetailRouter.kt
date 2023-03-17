package com.example.movielist.presentation.detail

import androidx.navigation.NavController

// Fragment scoped
class DetailRouter : DetailContract.Router {

    private var navController: NavController? = null

    override fun bind(newNavController: NavController) {
        navController = newNavController
    }

    override fun unBind() {
        navController = null
    }
}