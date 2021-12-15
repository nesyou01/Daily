package com.nesyou.daily.core.domain.models


import java.lang.Exception

enum class Status {
    SUCCESS,
    ERROR,
    LOADING
}

data class Resource<out T>(
    val status: Status? = null,
    val data: T? = null,
    val error: Exception? = null
) {
    companion object {
        fun <T> success(data: T? = null): Resource<T> =
            Resource(status = Status.SUCCESS, data = data, error = null)

        fun <T> error(exception: Exception): Resource<T> =
            Resource(status = Status.ERROR, data = null, error = exception)

        fun <T> loading(data: T? = null): Resource<T> =
            Resource(status = Status.LOADING, data = data, error = null)

        fun <T> reset(): Resource<T> =
            Resource(status = null, data = null, error = null)
    }
}
