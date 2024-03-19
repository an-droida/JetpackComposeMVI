package com.androida.eventbus.handlers

sealed class ApiResult<T>
data class Success<T>(val value: T) : ApiResult<T>()
data class Failure<T>(val error: Error) : ApiResult<T>()

sealed class Error
data object UnauthorizedError : Error()
data class ApiError(val msg: String) : Error()
data class GenericError(val throwable: Throwable) : Error()
data object NoNetworkError : Error()
data class ServerConnectionError(val message: String?) : Error()

suspend fun <T> ApiResult<T>.onSuccess(
    onSuccess: suspend (T) -> Unit
): ApiResult<T> {
    if (this is Success) {
        onSuccess(value)
    }
    return this
}

suspend fun <T> ApiResult<T>.onFailure(
    onFailure: suspend () -> Unit
): ApiResult<T> {
    if (this is Failure) {
        onFailure()
    }
    return this
}

fun <T, U> ApiResult<T>.map(
    mapper: (T) -> U
): ApiResult<U> {
    return when (this) {
        is Success -> Success(mapper(value))
        is Failure -> Failure(error)
    }
}