package com.yugyd.hida.core.coroutines

sealed interface Result<T> {
    data class Success<T>(val data: T) : Result<T>
    data class Error<T>(val error: Throwable) : Result<T>

    companion object {
        inline fun <T> success(value: T): Result<T> = Success(value)
        inline fun <T> failure(exception: Throwable) = Error<T>(exception)
    }
}

inline fun <R> result(block: () -> R): Result<R> {
    return try {
        Result.success(block())
    } catch (e: Throwable) {
        Result.failure(e)
    }
}

inline fun <T> Result<T>.onFailure(action: (exception: Throwable) -> Unit): Result<T> {
    if (this is Result.Error) {
        action(error)
    }

    return this
}

inline fun <T> Result<T>.onSuccess(action: (value: T) -> Unit): Result<T> {
    if (this is Result.Success) {
        action(data)
    }

    return this
}
