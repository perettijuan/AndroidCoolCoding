package com.example.movielist.domain

import com.example.movielist.network.NetworkManager

interface NetworkRepository {

    fun getConnected(): Boolean
}

class NetworkRepositoryImpl(private val networkManager: NetworkManager) : NetworkRepository {

    override fun getConnected(): Boolean {
        return networkManager.hasConnection()
    }
}