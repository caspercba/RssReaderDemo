package com.gaspardeelias.rssreaderdemo.repository.model

sealed class ResultWrapper<out T> {
    data class Success<out T>(val value: T) : ResultWrapper<T>()
    data class GenericError(val e: Exception) : ResultWrapper<Nothing>()
    object NetworkError : ResultWrapper<Nothing>()
}