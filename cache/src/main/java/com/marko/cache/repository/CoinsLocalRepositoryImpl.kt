package com.marko.cache.repository

import com.marko.cache.db.CoinsDatabase
import com.marko.cache.mapper.CoinsCacheMapper
import com.marko.data.entity.CoinData
import com.marko.data.repository.CoinsLocalRepository

class CoinsLocalRepositoryImpl(
		private val coinsDatabase: CoinsDatabase
): CoinsLocalRepository {

	override suspend fun getAllCoins(): List<CoinData> {
		return CoinsCacheMapper.mapToData(coinsDatabase.instance!!.coinsDao().getAllCoins())
	}

	override suspend fun getCoin(id: Int): CoinData {
		return CoinsCacheMapper.mapToData(coinsDatabase.instance!!.coinsDao().getCoin(id.toLong()))
	}

	override suspend fun saveCoins(coins: List<CoinData>) {
		TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
	}

	override suspend fun saveCoin(coin: CoinData) {
		TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
	}

	override var isCached: Boolean
		get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.
		set(value) {}
	override var isExpired: Boolean
		get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.
		set(value) {}
	override var cacheExpirationDate: Long
		get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.
		set(value) {}
}