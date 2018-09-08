package com.marko.remote.mapper

import com.marko.data.entity.CoinData
import com.marko.remote.entity.CoinRemote

object CoinsRemoteMapper: RemoteMapper<CoinData, CoinRemote> {

	override fun mapFromData(data: CoinData): CoinRemote {
		return CoinRemote(
				id = data.id,
				name = data.name,
				symbol = data.symbol
		)
	}

	override fun mapFromData(datas: List<CoinData>): List<CoinRemote> {
		return datas.map { mapFromData(it) }
	}

	override fun mapToData(remote: CoinRemote): CoinData {
		return CoinData(
				id = remote.id,
				name = remote.name,
				symbol = remote.symbol
		)
	}

	override fun mapToData(remotes: List<CoinRemote>): List<CoinData> {
		return remotes.map { mapToData(it) }
	}
}