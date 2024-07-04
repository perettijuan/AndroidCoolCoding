package com.jpp.usercentricschallenge.consent

interface ConsentCostModifier {
    fun tag(): String
    fun apply(consentCost: Double): Double
}