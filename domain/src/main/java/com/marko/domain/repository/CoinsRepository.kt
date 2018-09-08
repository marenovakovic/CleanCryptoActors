package com.marko.domain.repository

import com.marko.domain.entity.CoinEntity

interface CoinsRepository {

	suspend fun getAllCoins(): List<CoinEntity>

	suspend fun getCoin(id: Int): CoinEntity
}