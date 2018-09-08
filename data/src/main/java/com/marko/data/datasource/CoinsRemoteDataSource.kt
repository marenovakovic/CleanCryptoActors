package com.marko.data.datasource

import com.marko.data.entity.CoinData
import com.marko.data.repository.CoinsRemoteRepository

class CoinsRemoteDataSource(
		private val remoteRepository: CoinsRemoteRepository
): CoinsDataSource {

	override suspend fun getAllCoins(): List<CoinData> = remoteRepository.getAllCoins()

	override suspend fun getCoin(id: Int): CoinData = remoteRepository.getCoin(id)

	override suspend fun saveCoins(coins: List<CoinData>) = throw IllegalAccessException("RemoteDataSource doesn't caches data")

	override suspend fun saveCoin(coin: CoinData) = throw IllegalAccessException("RemoteDataSource doesn't caches data")

	override var isCached: Boolean
		get() = throw IllegalAccessException("RemoteDataSource doesn't caches data")
		set(value) = throw IllegalAccessException("RemoteDataSource doesn't caches data")

	override var isExpired: Boolean
		get() = throw IllegalAccessException("RemoteDataSource doesn't caches data")
		set(value) = throw IllegalAccessException("RemoteDataSource doesn't caches data")

	override var cacheExpirationDate: Long
		get() = throw IllegalAccessException("RemoteDataSource doesn't caches data")
		set(value) = throw IllegalAccessException("RemoteDataSource doesn't caches data")
}