package com.jpp.koinvshilt

import android.app.Application
import org.koin.core.context.startKoin

class KoinHiltApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {}
    }
}