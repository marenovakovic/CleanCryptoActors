package com.marko.domain.usecase

abstract class UseCase<in P, R> {

	protected abstract suspend fun execute(parameters: P): R

	suspend operator fun invoke(parameters: P): R {
		return execute(parameters)
	}
}

suspend operator fun <R> UseCase<Unit, R>.invoke() = this(Unit)