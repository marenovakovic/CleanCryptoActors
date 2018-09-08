package com.marko.cache.mapper

import com.marko.cache.entity.CoinCache
import com.marko.data.entity.CoinData

object CoinsCacheMapper : CacheMapper<CoinData, CoinCache> {

	override fun mapFromData(data: CoinData): CoinCache {
		return CoinCache(
			id = data.id,
			name = data.name,
			symbol = data.symbol
		)
	}

	override fun mapFromData(datas: List<CoinData>): List<CoinCache> {
		return datas.map { mapFromData(it) }
	}

	override fun mapToData(cache: CoinCache): CoinData {
		return CoinData(
			id = cache.id,
			name = cache.name,
			symbol = cache.symbol
		)
	}

	override fun mapToData(caches: List<CoinCache>): List<CoinData> {
		return caches.map { mapToData(it) }
	}
}