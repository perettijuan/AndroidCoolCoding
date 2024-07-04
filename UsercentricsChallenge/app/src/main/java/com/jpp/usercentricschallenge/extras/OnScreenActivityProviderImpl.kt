package com.jpp.usercentricschallenge.extras

import android.app.Activity
import android.app.Application
import android.content.Context
import android.os.Bundle
import java.util.concurrent.atomic.AtomicReference

class OnScreenActivityProviderImpl :
    OnScreenActivityProvider,
    Application.ActivityLifecycleCallbacks {
    private val activityRef: AtomicReference<Activity?> = AtomicReference(null)

    override fun initialize(context: Context) {
        (context.applicationContext as Application).registerActivityLifecycleCallbacks(this)
    }

    override fun get(): Activity? = activityRef.get()

    override fun onActivityCreated(
        activity: Activity,
        bundle: Bundle?,
    ) {
        activityRef.set(activity)
    }

    override fun onActivityDestroyed(activity: Activity) {
        if (activityRef.get() == activity) {
            activityRef.set(null)
        }
    }

    override fun onActivityStarted(activity: Activity) = Unit

    override fun onActivityResumed(activity: Activity) = Unit

    override fun onActivityPaused(activity: Activity) = Unit

    override fun onActivityStopped(activity: Activity) = Unit

    override fun onActivitySaveInstanceState(
        activity: Activity,
        bundle: Bundle,
    ) = Unit
}
