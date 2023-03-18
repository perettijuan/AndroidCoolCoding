package com.jpp.koinvshilt

import com.example.movielist.network.NetworkManager
import org.koin.core.module.Module
import org.koin.dsl.module


val appInstances: Module = module {
    single<NetworkManager> { NetworkManagerImpl() }
}