package com.marko.data.repository

import com.marko.data.entity.CoinData

interface CoinsRemoteRepository {

	suspend fun getAllCoins(): List<CoinData>

	suspend fun getCoin(id: Int): CoinData
}