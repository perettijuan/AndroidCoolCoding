package com.jpp.usercentricschallenge.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jpp.usercentricschallenge.consent.ConsentCollector
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel
    @Inject
    constructor(
        private val consentCollector: ConsentCollector,
    ) : ViewModel() {
        private val _state = MutableStateFlow(HomeViewState())
        val state: StateFlow<HomeViewState> = _state

        init {
            viewModelScope.launch {
                val canCollect = consentCollector.canCollect()
                _state.value = HomeViewState(collectButtonEnabled = canCollect)
            }
        }

        fun collectConsent() {
            viewModelScope.launch {
                val cost = consentCollector.collect()
                _state.value = HomeViewState(cost = cost)
            }
        }
    }
