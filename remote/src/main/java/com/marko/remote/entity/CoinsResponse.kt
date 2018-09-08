package com.marko.remote.entity

import com.google.gson.annotations.SerializedName

data class CoinsResponse(
		@SerializedName("data")
		val coins: List<CoinRemote>
)