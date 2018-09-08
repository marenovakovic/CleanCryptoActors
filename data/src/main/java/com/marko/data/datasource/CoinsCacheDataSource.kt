package com.marko.data.datasource

import com.marko.data.entity.CoinData
import com.marko.data.repository.CoinsCacheRepository

class CoinsCacheDataSource(
	private val cacheRepository: CoinsCacheRepository
) : CoinsDataSource {

	override suspend fun getAllCoins(): List<CoinData> = cacheRepository.getAllCoins()

	override suspend fun getCoin(id: Int): CoinData = cacheRepository.getCoin(id)

	override suspend fun saveCoins(coins: List<CoinData>) = cacheRepository.saveCoins(coins)

	override suspend fun saveCoin(coin: CoinData) = cacheRepository.saveCoin(coin)

	override suspend fun clear() = cacheRepository.clear()

	override var isCached: Boolean
		get() = cacheRepository.isCached
		set(value) {
			cacheRepository.isCached = value
		}

	override var isExpired: Boolean
		get() = cacheRepository.isExpired
		set(value) = throw IllegalAccessException(
			"CoinsCacheDataSource isExpired property can't be set externally"
		)

	override var cacheExpirationDate: Long
		get() = throw IllegalAccessException(
			"CoinsCacheDataSource lastCacheTime property can't be accessed externally"
		)
		set(value) = throw IllegalAccessException(
			"CoinsCacheDataSource lastCacheTime property can't be set externally"
		)
}