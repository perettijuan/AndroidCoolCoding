package com.jpp.koinvshilt

import android.app.Application
import com.example.movielist.di.ModuleInstanceProvider

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        ModuleInstanceProvider.initialize(NetworkManagerImpl())
    }
}