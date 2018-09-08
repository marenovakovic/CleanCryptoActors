package com.marko.remote.api

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.experimental.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

private const val READ_TIMEOUT = 15L
private const val WRITE_TIMEOUT = 15L

private const val BASE_URL = " https://api.coinmarketcap.com/v2/"

object CoinsApi {

	private val okHttpClient = OkHttpClient.Builder()
			.writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
			.readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
			.build()

	private val retrofitBuilder = Retrofit.Builder()
			.baseUrl(BASE_URL)
			.client(okHttpClient)
			.addCallAdapterFactory(CoroutineCallAdapterFactory())
			.addConverterFactory(GsonConverterFactory.create())
			.build()

	val coinsService: CoinsService = retrofitBuilder.create(CoinsService::class.java)
}