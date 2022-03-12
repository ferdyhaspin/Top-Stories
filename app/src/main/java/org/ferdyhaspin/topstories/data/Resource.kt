package org.ferdyhaspin.topstories.data

sealed class Resource<T>(
    val data: T? = null,
    val error: Error? = null,
    val path: String? = null,

    ) {
    class Success<T>(data: T) : Resource<T>(data)
    class Loading<T> : Resource<T>()
    class DataError<T>(error: Error) : Resource<T>(error = error)
}