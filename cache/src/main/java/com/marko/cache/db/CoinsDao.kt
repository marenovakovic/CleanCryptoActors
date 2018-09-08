package com.marko.cache.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.marko.cache.entity.CoinCache

@Dao
interface CoinsDao {

	@Query("SELECT * FROM coins")
	fun getAllCoins(): List<CoinCache>

	@Query("SELECT * FROM coins WHERE id = :id")
	fun getCoin(id: Long): CoinCache

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	fun insertCoins(coins: List<CoinCache>)

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	fun insertCoin(coin: CoinCache)

	@Query("DELETE FROM coins")
	fun deleteCoins()
}