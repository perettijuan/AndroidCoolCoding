package com.example.movielist.domain

import com.example.movielist.network.NetworkManager

internal interface NetworkRepository {

    fun getConnected(): Boolean
}

internal class NetworkRepositoryImpl(private val networkManager: NetworkManager) : NetworkRepository {

    override fun getConnected(): Boolean {
        return networkManager.hasConnection()
    }
}