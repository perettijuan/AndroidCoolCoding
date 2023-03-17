package com.jpp.toprated.presentation.list

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
        // TODO
//        DetailParam.selectedMovieId = item.id
//        navController?.navigate(R.id.detailFragment)
    }
}