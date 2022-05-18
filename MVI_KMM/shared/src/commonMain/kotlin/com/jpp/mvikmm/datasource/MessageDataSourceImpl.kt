package com.jpp.mvikmm.datasource

class MessageDataSourceImpl : MessageDataSource {

    private var attempt = 0

    override fun nextMessage(): String {
        attempt += 1
        return "This is attempt $attempt"
    }
}