package com.marko.domain

sealed class Result<out T : Any> {

	data class Success<out T : Any>(val data: T) : Result<T>()
	data class Error(val throwable: Throwable) : Result<Nothing>()
	object Loading : Result<Nothing>()

	inline fun <R> fold(success: (T) -> R, error: (e: Throwable) -> R, loading: () -> R) {
		when (this) {
			is Success -> success(data)
			is Error -> error(throwable)
			is Loading -> loading()
		}
	}
}