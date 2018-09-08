package com.marko.remote.repository

import com.marko.data.entity.CoinData
import com.marko.data.repository.CoinsRemoteRepository
import com.marko.remote.api.CoinsApi
import com.marko.remote.mapper.CoinsRemoteMapper

class CoinsRemoteRepositoryImpl : CoinsRemoteRepository {

	override suspend fun getAllCoins(): List<CoinData> {
		val coins = CoinsApi.coinsService.getAllCoins().await().coins
		return CoinsRemoteMapper.mapToData(coins)
	}

	override suspend fun getCoin(id: Int): CoinData {
		val coin = CoinsApi.coinsService.getCoin(id).await()
		return CoinsRemoteMapper.mapToData(coin)
	}
}