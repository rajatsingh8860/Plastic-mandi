package com.example.plasticmandi.utils

sealed class Resource<T>(
    val data: T? = null,
    val code: Int? = null,
    val message: String? = null,

    ) {
    class Success<T>(data: T, code: Int) : Resource<T>(data, code)
    class Error<T>(message: String, code: Int, data: T? = null) : Resource<T>(data, code, message)
    class Loading<T> : Resource<T>()
}