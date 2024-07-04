package com.jpp.usercentricschallenge.ui.home

import com.jpp.usercentricschallenge.consent.ConsentCollector
import com.jpp.usercentricschallenge.consent.ConsentCollectorImpl
import com.jpp.usercentricschallenge.consent.ServiceCostProvider
import com.jpp.usercentricschallenge.consent.ServiceCostProviderImpl
import com.jpp.usercentricschallenge.extras.OnScreenActivityProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
class HomeHiltViewModel {
    @ViewModelScoped
    @Provides
    fun providesServiceCostProvider(): ServiceCostProvider = ServiceCostProviderImpl()

    @ViewModelScoped
    @Provides
    fun providesConsentCollector(
        onScreenActivityProvider: OnScreenActivityProvider,
        serviceCostProvider: ServiceCostProvider,
    ): ConsentCollector = ConsentCollectorImpl(onScreenActivityProvider, serviceCostProvider)
}
