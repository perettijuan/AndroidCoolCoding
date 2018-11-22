package com.jpp.architecturecomponents.di

import com.jpp.architecturecomponents.ApplicationImpl
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, AndroidInjectionModule::class, BuildersModule::class, ViewModelModule::class])
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: ApplicationImpl): Builder

        fun build(): AppComponent
    }

    fun inject(app: ApplicationImpl)
}