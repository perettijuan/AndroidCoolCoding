package com.jpp.usercentricschallenge.consent

/**
 * Provides the costs associated with the services that can be used by the application.
 * This provider could easily be replaced by a network call or a local database.
 */
interface ServiceCostProvider {
    /**
     * Provides the costs associated with the services that can be used by the application.
     * @return a list of [ServiceCost] objects.
     */
    fun getCosts(): List<ServiceCost>
}
