package com.marko.cache.repository

import com.marko.cache.db.CoinsDatabase
import com.marko.cache.mapper.CoinsCacheMapper
import com.marko.data.entity.CoinData
import com.marko.data.repository.CoinsCacheRepository
import com.marko.domain.prefs.PreferenceStorage

class CoinsCacheRepositoryImpl(
	private val coinsDatabase: CoinsDatabase,
	private val prefs: PreferenceStorage
) : CoinsCacheRepository {

	override suspend fun getAllCoins(): List<CoinData> {
		return CoinsCacheMapper.mapToData(coinsDatabase.coinsDao().getAllCoins())
	}

	override suspend fun getCoin(id: Int): CoinData {
		return CoinsCacheMapper.mapToData(coinsDatabase.coinsDao().getCoin(id.toLong()))
	}

	override suspend fun saveCoins(coins: List<CoinData>) {
		coinsDatabase.coinsDao().saveCoins(CoinsCacheMapper.mapFromData(coins))
		lastCacheTime = System.currentTimeMillis()
	}

	override suspend fun saveCoin(coin: CoinData) {
		coinsDatabase.coinsDao().saveCoin(CoinsCacheMapper.mapFromData(coin))
	}

	override suspend fun clear() {
		coinsDatabase.coinsDao().clear()
	}

	override var isCached: Boolean
		get() = prefs.lastCacheTime != -1L
		set(value) = throw IllegalAccessException("isCached is read only property")
	override var isExpired: Boolean
		get() = prefs.lastCacheTime > 2 * DAY
		set(value) = throw IllegalAccessException("isExpired is read only property")
	override var lastCacheTime: Long
		get() = prefs.lastCacheTime
		set(value) {
			prefs.lastCacheTime = value
		}

	companion object {
		private const val SECOND = 1000
		private const val MINUTE = 60 * SECOND
		private const val HOUR = 60 * MINUTE
		private const val DAY = 24 * HOUR
	}
}