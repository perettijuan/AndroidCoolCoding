package com.jpp.usercentricschallenge.consent

import com.usercentrics.sdk.v2.settings.data.UsercentricsService

class ConsentCostModifierFactoryImpl : ConsentCostModifierFactory {
    override fun createFor(service: UsercentricsService): ConsentCostModifier? =
        when {
            service.dataCollectedList.containsAll(bankingSnoopyFilter) -> BankingSnoopyModifier()
            service.dataCollectedList.containsAll(whyDoYouCareFilter) -> WhyDoYouCareModifier()
            service.dataCollectedList.size <= goodCitizenServicesMaxCount -> TheGoodCitizen()
            else -> null
        }

    private class BankingSnoopyModifier : ConsentCostModifier {
        override fun tag() = "BankingSnoopyModifier"

        override fun apply(consentCost: Double): Double = consentCost + (consentCost * 0.10)
    }

    private class WhyDoYouCareModifier : ConsentCostModifier {
        override fun tag() = "WhyDoYouCareModifier"

        override fun apply(consentCost: Double): Double = consentCost + (consentCost * 0.27)
    }

    private class TheGoodCitizen : ConsentCostModifier {
        override fun tag() = "TheGoodCitizen"

        override fun apply(consentCost: Double): Double = consentCost - (consentCost * 0.10)
    }

    companion object {
        private val bankingSnoopyFilter =
            listOf("Purchase activity", "Bank details", "Credit and debit card number")
        private val whyDoYouCareFilter =
            listOf("Search terms", "Geographic location", "IP Address")
        private val goodCitizenServicesMaxCount = 4
    }
}
