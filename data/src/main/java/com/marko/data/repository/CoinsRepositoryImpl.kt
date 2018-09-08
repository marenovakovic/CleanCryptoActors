package com.marko.data.repository

import com.marko.data.datasource.CoinsDataSourceFactory
import com.marko.data.mapper.CoinsDataMapper
import com.marko.domain.entity.CoinEntity
import com.marko.domain.repository.CoinsRepository

class CoinsRepositoryImpl(
		private val dataSourceFactory: CoinsDataSourceFactory
): CoinsRepository {

	override suspend fun getAllCoins(): List<CoinEntity> {
		val coins = dataSourceFactory.dataSource.getAllCoins()
		return CoinsDataMapper.mapToEntity(coins)
	}

	override suspend fun getCoin(id: Int): CoinEntity {
		TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
	}
}