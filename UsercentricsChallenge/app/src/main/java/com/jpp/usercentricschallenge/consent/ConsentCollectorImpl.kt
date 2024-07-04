package com.jpp.usercentricschallenge.consent

import android.util.Log
import com.jpp.usercentricschallenge.LOG_TAG
import com.jpp.usercentricschallenge.extras.OnScreenActivityProvider
import com.usercentrics.sdk.Usercentrics
import com.usercentrics.sdk.UsercentricsBanner
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume

class ConsentCollectorImpl(
    private val activityProvider: OnScreenActivityProvider,
    private val serviceCostProvider: ServiceCostProvider,
) : ConsentCollector {
    // map of  <templateId, ServiceCost>
    private val serviceCosts = mutableMapOf<String, ServiceCost>()

    override suspend fun canCollect(): Boolean =
        suspendCancellableCoroutine { continuation ->
            Usercentrics.isReady(
                onSuccess = { status ->
                    Log.d(LOG_TAG, "Usercentrics is ready: $status")

                    if (serviceCosts.isEmpty()) {
                        serviceCosts.putAll(findServiceCostsForCalculation(serviceCostProvider.getCosts()))
                    }

                    Log.d(LOG_TAG, "serviceCosts have: $serviceCosts")
                    continuation.resume(true)
                },
                onFailure = { error ->
                    Log.d(LOG_TAG, "Usercentrics failed: $error")
                    continuation.resume(false)
                },
            )
        }

    override suspend fun collect(): Int =
        suspendCancellableCoroutine { continuation ->

            val activity =
                activityProvider.get()
                    ?: error("Consent can only be collected from an active screen.")

            val banner = UsercentricsBanner(activity)
            banner.showSecondLayer { userResponse ->
                var cost = 0
                userResponse?.consents?.forEach { consent ->
                    if (consent.status) {
                        val serviceCost = serviceCosts[consent.templateId]
                        if (serviceCost != null) {
                            // Requirement 1: print in console the cost of each service
                            Log.d(LOG_TAG, "For service ${serviceCost.dataCollectedStateName} the cost is ${serviceCost.cost}")
                            cost += serviceCost.cost
                        }
                    }
                }
                // Requirement 2: print in console the total cost of the user's consent
                Log.d(LOG_TAG, "Total cost of the user's consent is $cost")
                continuation.resume(cost)
            }
        }

    /**
     * Iterate all available services in the CMP data and find the costs associated with the services that will be
     * used to calculate the cost of the user's consent.
     */
    private fun findServiceCostsForCalculation(defaultCosts: List<ServiceCost>): MutableMap<String, ServiceCost> {
        val costs = mutableMapOf<String, ServiceCost>()
        Usercentrics.instance.getCMPData().services.forEach { service ->
            defaultCosts.forEach { serviceCost ->
                val templateId = service.templateId
                if (service.dataCollectedList.contains(serviceCost.dataCollectedStateName) && templateId != null) {
                    costs[templateId] = serviceCost
                }
            }
        }
        return costs
    }
}
