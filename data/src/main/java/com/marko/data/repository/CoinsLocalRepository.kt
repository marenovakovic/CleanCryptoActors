package com.marko.data.repository

import com.marko.data.entity.CoinData

interface CoinsLocalRepository {

	suspend fun getAllCoins(): List<CoinData>

	suspend fun getCoin(id: Int): CoinData

	suspend fun saveCoins(coins: List<CoinData>)

	suspend fun saveCoin(coin: CoinData)

	var isCached: Boolean

	var isExpired: Boolean

	var cacheExpirationDate: Long
}