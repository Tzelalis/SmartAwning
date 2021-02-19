package com.example.smartawning.utils

import retrofit2.Response
import java.io.IOException
import java.lang.Exception

fun <T : Any> Response<T>.handleExternalApiFormat(): T {
    val body = body()
    return if (isSuccessful && body != null) {
        body
    } else {
        throw IOException()
    }
}

suspend fun <R : Any> apiCall(function: suspend () -> Response<R>): R {
    return function.invoke().handleExternalApiFormat()
}