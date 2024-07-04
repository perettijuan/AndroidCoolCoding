package com.jpp.usercentricschallenge.extras

import android.app.Activity
import android.content.Context

/**
 * Provides the activity that is currently on screen.
 */
interface OnScreenActivityProvider {
    /**
     * Initializes the activity provider.
     */
    fun initialize(context: Context)

    /**
     * Returns the activity that is currently on screen.
     *
     * @return The activity that is currently on screen or null if no activity is on screen.
     */
    fun get(): Activity?
}
