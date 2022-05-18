package com.jpp.mvikmm.presentation

import com.jpp.mvikmm.model.MessageRepository

class UserIntentImpl(private val repository: MessageRepository) : UserIntent {

    override fun onButtonPressed() {
        repository.produceMessage()
    }
}