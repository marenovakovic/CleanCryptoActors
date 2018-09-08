package com.marko.presentation.mapper

import com.marko.domain.entity.CoinEntity
import com.marko.presentation.entity.Coin

object CoinsPresentationMapper: PresentationMapper<CoinEntity, Coin> {

	override fun mapFromEntity(entity: CoinEntity): Coin {
		return Coin(
				id = entity.id,
				name = entity.name,
				symbol = entity.symbol
		)
	}

	override fun mapFromEntity(entities: List<CoinEntity>): List<Coin> {
		return entities.map { mapFromEntity(it) }
	}

	override fun mapToEntity(presentation: Coin): CoinEntity {
		return CoinEntity(
				id = presentation.id,
				name = presentation.name,
				symbol = presentation.symbol
		)
	}

	override fun mapToEntity(presentations: List<Coin>): List<CoinEntity> {
		return presentations.map { mapToEntity(it) }
	}
}