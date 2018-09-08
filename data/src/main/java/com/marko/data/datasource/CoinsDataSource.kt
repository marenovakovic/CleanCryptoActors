package com.marko.data.datasource

import com.marko.data.entity.CoinData

interface CoinsDataSource {

	suspend fun getAllCoins(): List<CoinData>

	suspend fun getCoin(id: Int): CoinData

	suspend fun saveCoins(coins: List<CoinData>)

	suspend fun saveCoin(coin: CoinData)

	var isCached: Boolean

	var isExpired: Boolean

	var cacheExpirationDate: Long
}