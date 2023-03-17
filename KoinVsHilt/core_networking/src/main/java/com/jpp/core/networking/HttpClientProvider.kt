package com.jpp.core.networking

import android.net.sip.SipErrorCode.TIME_OUT
import android.util.Log
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import io.ktor.client.features.observer.*

interface HttpClientProvider {
    fun provide(): HttpClient

    companion object {
        fun locateInstance(): HttpClientProvider {
            return HttpClientProviderImpl()
        }
    }
}

internal class HttpClientProviderImpl : HttpClientProvider {

    init {
        Log.d("JPPLOG", "Init")
    }

    override fun provide(): HttpClient {
        return HttpClient(Android) {
            followRedirects = false
            expectSuccess = false

            install(JsonFeature) {
                serializer = KotlinxSerializer()

                engine {
                    connectTimeout = TIME_OUT
                    socketTimeout = TIME_OUT
                }
            }

            install(Logging) {
                logger = object : Logger {
                    override fun log(message: String) {
                        Log.v("Logger Ktor =>", message)
                    }

                }
                level = LogLevel.ALL
            }

            install(ResponseObserver) {
                onResponse { response ->
                    Log.d("HTTP status:", "${response.status.value}")
                }
            }
        }
    }
}