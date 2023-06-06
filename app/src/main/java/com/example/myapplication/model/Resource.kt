package com.example.myapplication.model

sealed class Resource<T>(data: T? = null, throwable: Throwable? = null) {
    class Loading<T> : Resource<T>()
    data class Success<T>(val data: T) : Resource<T>(data = data)
    data class Error<T>(val t: Throwable) : Resource<T>(throwable = t)
}