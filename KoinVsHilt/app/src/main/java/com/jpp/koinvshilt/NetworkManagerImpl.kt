package com.jpp.koinvshilt

import com.example.movielist.network.NetworkManager

class NetworkManagerImpl : NetworkManager {

    override fun hasConnection(): Boolean {
        return true // for simplicity
    }
}