package com.gaspardeelias.rssreaderdemo.repository.model

sealed class ResultWrapper<out T> {
    data class Success<out T>(val value: T) : ResultWrapper<T>()
    data class GenericError<out T>(val t: Throwable) : ResultWrapper<T>()
    data class NetworkError<out T>(val code: Int) : ResultWrapper<T>()
    object AuthError : ResultWrapper<Nothing>()
}