package com.example.myapplication.model

sealed interface Resource<T> {
    class Loading<T> : Resource<T>
    data class Success<T>(val data: T) : Resource<T>
    data class Error<T>(val t: Throwable) : Resource<T>
}