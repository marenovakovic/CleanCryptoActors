package com.marko.data.datasource

class CoinsDataSourceFactory(
		private val coinsRemoteDataSource: CoinsRemoteDataSource,
		private val coinsLocalDataSource: CoinsLocalDataSource
) {
	val dataSource: CoinsDataSource
		get() = coinsRemoteDataSource
//			if (coinsLocalDataSource.isCached && coinsLocalDataSource.isExpired) coinsLocalDataSource
//			else coinsRemoteDataSource
}