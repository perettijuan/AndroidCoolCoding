package com.jpp.usercentricschallenge.consent

/**
 * Defines the behavior of the way that the application will collect the user consent.
 */
interface ConsentCollector {
    /**
     * Checks if the consent can be collected. Suspend is used since this check is async.
     *
     * @return true if the consent can be collected, false otherwise.
     */
    suspend fun canCollect(): Boolean

    suspend fun collect(): Int
}
