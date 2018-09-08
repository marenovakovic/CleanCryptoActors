package com.marko.data.repository

import com.marko.data.entity.CoinData

interface CoinsCacheRepository {

	suspend fun getAllCoins(): List<CoinData>

	suspend fun getCoin(id: Int): CoinData

	suspend fun saveCoins(coins: List<CoinData>)

	suspend fun saveCoin(coin: CoinData)

	suspend fun clear()

	var isCached: Boolean

	var isExpired: Boolean

	var lastCacheTime: Long
}