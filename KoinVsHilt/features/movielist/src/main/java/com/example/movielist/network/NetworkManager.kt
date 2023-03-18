package com.example.movielist.network

/**
 * This interface MUST be provided by the :app module. It is an example
 * of how the consumer module can provide an instance consumed by the
 * feature module.
 */
interface NetworkManager {

    fun hasConnection(): Boolean
}