package com.marko.data.datasource

import com.marko.data.entity.CoinData
import com.marko.data.repository.CoinsLocalRepository

class CoinsLocalDataSource(
		private val localRepository: CoinsLocalRepository
): CoinsDataSource {

	override suspend fun getAllCoins(): List<CoinData> = localRepository.getAllCoins()

	override suspend fun getCoin(id: Int): CoinData = localRepository.getCoin(id)

	override suspend fun saveCoins(coins: List<CoinData>) = localRepository.saveCoins(coins)

	override suspend fun saveCoin(coin: CoinData) = localRepository.saveCoin(coin)

	override var isCached: Boolean
		get() = localRepository.isCached
		set(value) {
			localRepository.isCached = value
		}

	override var isExpired: Boolean
		get() = localRepository.isExpired
		set(value) = throw IllegalAccessException(
				"CoinsLocalDataSource isExpired property can't be set externally")

	override var cacheExpirationDate: Long
		get() = throw IllegalAccessException(
					"CoinsLocalDataSource cacheExpirationDate property can't be accessed externally")
		set(value) = throw IllegalAccessException(
				"CoinsLocalDataSource cacheExpirationDate property can't be set externally")
}