package com.jpp.samplelib

class Greeting {
    fun greeting(): String {
        return "Hello, ${Platform().platform}!"
    }
}