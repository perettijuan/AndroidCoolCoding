package com.jpp.usercentricschallenge.consent

import com.usercentrics.sdk.v2.settings.data.UsercentricsService

interface ConsentCostModifierFactory {
    fun createFor(service: UsercentricsService): ConsentCostModifier?
}
