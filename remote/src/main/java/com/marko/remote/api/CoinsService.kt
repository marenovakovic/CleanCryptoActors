package com.marko.remote.api

import com.marko.remote.entity.CoinsResponse
import com.marko.remote.entity.SingleCoinResponse
import kotlinx.coroutines.experimental.Deferred
import retrofit2.http.GET
import retrofit2.http.Path

interface CoinsService {

	@GET("listings/")
	fun getAllCoins(): Deferred<CoinsResponse>

	@GET("ticker/{id}")
	fun getCoin(@Path("id") id: Int): Deferred<SingleCoinResponse>
}