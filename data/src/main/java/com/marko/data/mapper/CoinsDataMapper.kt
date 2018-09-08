package com.marko.data.mapper

import com.marko.data.entity.CoinData
import com.marko.domain.entity.CoinEntity

object CoinsDataMapper : DataMapper<CoinEntity, CoinData> {

	override fun mapFromEntity(entity: CoinEntity): CoinData {
		return CoinData(
			id = entity.id,
			name = entity.name,
			symbol = entity.symbol
		)
	}

	override fun mapFromEntity(entities: List<CoinEntity>): List<CoinData> {
		return entities.map { mapFromEntity(it) }
	}

	override fun mapToEntity(data: CoinData): CoinEntity {
		return CoinEntity(
			id = data.id,
			name = data.name,
			symbol = data.symbol
		)
	}

	override fun mapToEntity(datas: List<CoinData>): List<CoinEntity> {
		return datas.map { mapToEntity(it) }
	}
}