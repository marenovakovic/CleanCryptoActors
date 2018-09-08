package com.marko.domain.usecase

import com.marko.domain.entity.CoinEntity
import com.marko.domain.repository.CoinsRepository

class GetCoins(
	private val coinsRepository: CoinsRepository
) : UseCase<Unit, List<CoinEntity>>() {

	override suspend fun execute(parameters: Unit): List<CoinEntity> {
		return coinsRepository.getAllCoins()
	}
}