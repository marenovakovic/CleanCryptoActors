package com.marko.data.datasource

class CoinsDataSourceFactory(
	val coinsCacheDataSource: CoinsCacheDataSource,
	private val coinsRemoteDataSource: CoinsRemoteDataSource
) {
	val dataSource: CoinsDataSource
		get() =
			if (coinsCacheDataSource.isCached && coinsCacheDataSource.isExpired) coinsCacheDataSource
			else coinsRemoteDataSource
}