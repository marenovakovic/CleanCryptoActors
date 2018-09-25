package com.marko.domain.usecase

import com.marko.domain.Result
import kotlinx.coroutines.experimental.channels.Channel

abstract class UseCase<in P, R> {

	protected abstract suspend fun execute(parameters: P): R

	suspend operator fun invoke(parameters: P, channel: Channel<Result<R>>) {
		channel.send(Result.Loading)
		try {
			execute(parameters).let {
				channel.send(Result.Success(it))
			}
		} catch (e: Exception) {
			channel.send(Result.Error(e))
		}
	}

	suspend operator fun invoke(parameters: P): Channel<Result<R>> {
		val channel = Channel<Result<R>>()
		this(parameters, channel)
		return channel
	}
}

suspend operator fun <R> UseCase<Unit, R>.invoke() = this(Unit)