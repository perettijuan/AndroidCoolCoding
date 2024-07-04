package com.jpp.usercentricschallenge.consent

import android.util.Log
import com.jpp.usercentricschallenge.LOG_TAG
import com.jpp.usercentricschallenge.extras.OnScreenActivityProvider
import com.usercentrics.sdk.UsercentricsBanner

class ConsentCollectorImpl(
    private val activityProvider: OnScreenActivityProvider
) : ConsentCollector {

    override fun collect() {
        val activity = activityProvider.get() ?: error("Consent can only be collected from an active screen.")

        val banner = UsercentricsBanner(activity)
        banner.showSecondLayer { userResponse ->
            Log.d(LOG_TAG, "User response: $userResponse")
        }
    }
}