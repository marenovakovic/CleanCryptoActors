package com.marko.domain.usecase

import com.marko.domain.Result
import kotlinx.coroutines.experimental.channels.Channel
import kotlinx.coroutines.experimental.channels.ReceiveChannel

abstract class UseCase<in P, R> {

	protected abstract suspend fun execute(parameters: P): R

	suspend operator fun invoke(
		parameters: P,
		body: suspend (channel: ReceiveChannel<Result<R>>) -> Unit
	) {
		val channel = Channel<Result<R>>()

		body(channel)

		channel.send(Result.Loading)

		try {
			execute(parameters).let {
				channel.send(Result.Success(it))
			}
		} catch (e: Exception) {
			e.printStackTrace()
		} finally {
			channel.close()
		}
	}
}

suspend operator fun <R> UseCase<Unit, R>.invoke(body: suspend (channel: ReceiveChannel<Result<R>>) -> Unit) =
	this(Unit, body)