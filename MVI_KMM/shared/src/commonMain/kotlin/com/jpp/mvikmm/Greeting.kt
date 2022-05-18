package com.jpp.mvikmm

class Greeting {
    fun greeting(): String {
        return "Hello, ${Platform().platform}!"
    }
}