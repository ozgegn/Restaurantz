package com.ozge.restaurantz.domain.model

sealed class Resource<out R> {
    data class Success<out T>(val data: T?) : Resource<T>()
    data class Failure(val error: Error) : Resource<Nothing>()
    object Loading : Resource<Nothing>()
}