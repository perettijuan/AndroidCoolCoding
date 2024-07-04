package com.jpp.usercentricschallenge.consent

class ServiceCostProviderImpl : ServiceCostProvider {

    override fun getCosts(): List<ServiceCost> = definedCosts

    companion object {
        val definedCosts = listOf(
            ServiceCost(dataCollectedStateName = "Configuration of app settings", cost = 1),
            ServiceCost(dataCollectedStateName = "IP address", cost = 2),
            ServiceCost(dataCollectedStateName = "User behaviour", cost = 2),
            ServiceCost(dataCollectedStateName = "User agent", cost = 3),
            ServiceCost(dataCollectedStateName = "App crashes", cost = -2),
            ServiceCost(dataCollectedStateName = "Browser information", cost = 3),
            ServiceCost(dataCollectedStateName = "Credit and debit card number", cost = 4),
            ServiceCost(dataCollectedStateName = "First name", cost = 6),
            ServiceCost(dataCollectedStateName = "Geographic location", cost = 7),
            ServiceCost(dataCollectedStateName = "Date and time of visit", cost = 1),
            ServiceCost(dataCollectedStateName = "Advertising identifier", cost = 2),
            ServiceCost(dataCollectedStateName = "Bank details", cost = 5),
            ServiceCost(dataCollectedStateName = "Purchase activity", cost = 6),
            ServiceCost(dataCollectedStateName = "Internet service provider", cost = 4),
            ServiceCost(dataCollectedStateName = "JavaScript support", cost = -1),
        )
    }
}