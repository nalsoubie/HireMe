package com.example.hireme.data

interface AuthListener {
    fun onStarted()
    fun onSuccess()
    fun onFailure(message: String)
}

