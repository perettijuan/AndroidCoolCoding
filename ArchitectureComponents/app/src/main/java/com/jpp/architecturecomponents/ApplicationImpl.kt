package com.jpp.architecturecomponents

import android.app.Activity
import android.app.Application
import com.jpp.architecturecomponents.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

/**
 * Custom Application class. Implements [HasActivityInjector] in order to be able to bind this
 * instance to the Dagger graph.
 */
class ApplicationImpl : Application(), HasActivityInjector {

    @Inject
    lateinit var activityInjector: DispatchingAndroidInjector<Activity>


    override fun onCreate() {
        super.onCreate()
        DaggerAppComponent.builder()
                .application(this)
                .build()
                .inject(this)
    }

    override fun activityInjector(): AndroidInjector<Activity> = activityInjector
}