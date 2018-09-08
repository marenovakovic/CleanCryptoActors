package com.marko.cache.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.marko.cache.entity.CoinCache

@Database(entities = [CoinCache::class], version = 1)
abstract class CoinsDatabase : RoomDatabase() {

	abstract fun coinsDao(): CoinsDao

	var instance: CoinsDatabase? = null

	fun getInstance(context: Context): CoinsDatabase {
		return if (instance == null) {
			synchronized(Any()) {
				instance = Room.databaseBuilder(
					context.applicationContext,
					CoinsDatabase::class.java,
					"coinsdb.db"
				)
					.build()
				instance!!
			}
		} else instance!!
	}
}