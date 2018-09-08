package com.marko.cache.mapper

import com.marko.cache.entity.CoinCache
import com.marko.data.entity.CoinData

object CoinsCacheMapper: CacheMapper<CoinData, CoinCache> {

	override fun mapFromData(data: CoinData): CoinCache {
		TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
	}

	override fun mapFromData(datas: List<CoinData>): List<CoinCache> {
		TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
	}

	override fun mapToData(cache: CoinCache): CoinData {
		TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
	}

	override fun mapToData(caches: List<CoinCache>): List<CoinData> {
		TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
	}
}