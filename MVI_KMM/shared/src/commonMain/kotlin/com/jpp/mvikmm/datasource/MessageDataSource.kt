package com.jpp.mvikmm.datasource

interface MessageDataSource {
    fun nextMessage(): String
}