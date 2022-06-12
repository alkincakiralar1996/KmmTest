package com.devalkin.kmmtestapp

class Greeting {
    fun greeting(): String {
        return "Hello, ${Platform().platform}!"
    }
}