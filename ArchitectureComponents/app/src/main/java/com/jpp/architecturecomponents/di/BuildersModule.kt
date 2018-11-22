package com.jpp.architecturecomponents.di

import com.jpp.architecturecomponents.ui.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Binds all sub-components within the app.
 * The annotation @ContributesAndroidInjector frees us from having to create
 * separate components annotated with @Subcomponent.
 * How to create sub-modules and inject Fragments? ---> https://proandroiddev.com/exploring-the-new-dagger-android-module-9eb6075f1a46
 */
@Module
abstract class BuildersModule {

    @ContributesAndroidInjector
    abstract fun bindMainActivity(): MainActivity

    // Add bindings for other sub-components here
}