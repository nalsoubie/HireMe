package com.example.hireme.utils


interface DataHolder {
    fun <T : Any> hold(data: T)
}