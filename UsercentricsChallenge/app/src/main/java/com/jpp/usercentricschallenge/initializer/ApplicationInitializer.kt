package com.jpp.usercentricschallenge.initializer

import android.content.Context
import androidx.startup.Initializer
import com.jpp.usercentricschallenge.di.ApplicationCoroutineScopeQualifier
import com.jpp.usercentricschallenge.extras.OnScreenActivityProvider
import com.usercentrics.sdk.Usercentrics
import com.usercentrics.sdk.UsercentricsOptions
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.EntryPointAccessors
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * Host simple initialization logic for the application.
 */
class ApplicationInitializer : Initializer<Unit> {
    @Inject
    @ApplicationCoroutineScopeQualifier
    lateinit var appScope: CoroutineScope

    @Inject
    lateinit var onScreenActivityProvider: OnScreenActivityProvider

    override fun create(context: Context) {
        InitializerEntryPoint.resolve(context).inject(this)
        onScreenActivityProvider.initialize(context)

        appScope.launch {
            withContext(Dispatchers.IO) {
                val options = UsercentricsOptions(settingsId = SETTINGS_ID)
                Usercentrics.initialize(context, options)
            }
        }
    }

    override fun dependencies(): List<Class<out Initializer<*>>> = listOf()

    @EntryPoint
    @InstallIn(SingletonComponent::class)
    interface InitializerEntryPoint {
        fun inject(initializer: ApplicationInitializer)

        companion object {
            fun resolve(context: Context): InitializerEntryPoint {
                val appContext = context.applicationContext
                checkNotNull(appContext) {
                    "Application context is null"
                }
                return EntryPointAccessors.fromApplication(
                    appContext,
                    InitializerEntryPoint::class.java,
                )
            }
        }
    }

    companion object {
        const val SETTINGS_ID = "gChmbFIdL"
    }
}
