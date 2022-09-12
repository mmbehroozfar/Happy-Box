package com.mmb.happybox.shared

/**
 * A generic class that holds a value with its loading status.
 * @param <T>
 */
sealed class Result<out R> {
    data class Success<out T>(val data: T) : Result<T>()
    data class Error(val error: Exception) : Result<Nothing>()
    object Canceled : Result<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data= $data]"
            is Error -> "Error[exception= $error]"
            Canceled -> "Canceled"
        }
    }
}

inline fun <T> Result<T>.withResult(
    onLoading: (Boolean) -> Unit,
    onSuccess: (T) -> Unit,
    onError: (Throwable) -> Unit,
) {
    when (this) {
        Result.Canceled -> onLoading(false)
        is Result.Error -> {
            onLoading(false)
            onError(error)
        }
        is Result.Success -> {
            onLoading(false)
            onSuccess(data)
        }
    }
}

/**
 * [Result.Success.data] if [Result] is of type [Result.Success]
 */
fun <T> Result<T>.successOr(fallback: T): T {
    return (this as? Result.Success<T>)?.data ?: fallback
}

fun <T> Result<T>.isSuccess() = this is Result.Success

fun <T> Result<T>.isError() = this is Error

inline fun <R> Result<R>.onSuccess(action: (R) -> Unit): Result<R> {
    if (this is Result.Success) {
        action(this.data)
    }
    return this
}

inline fun <T> Result<T>.onError(onFailure: (Exception) -> Unit): Result<T> {
    if (this is Result.Error) onFailure(error)
    return this
}

fun <T> Result<T>?.dataOrNull(): T? {
    return if (this is Result.Success) this.data else null
}

fun <T> Result<T>?.requireData(): T {
    return (this as Result.Success).data
}