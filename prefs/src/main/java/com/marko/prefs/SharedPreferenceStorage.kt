package com.marko.prefs

import android.content.Context
import android.content.SharedPreferences
import androidx.annotation.WorkerThread
import com.marko.domain.prefs.PreferenceStorage
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class SharedPreferenceStorage(
	context: Context
) : PreferenceStorage {

	private val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

	override var lastCacheTime: Long by LongPreference(prefs, LAST_CACHE_TIME, -1L)

	companion object {
		const val PREFS_NAME = "coins"
		const val LAST_CACHE_TIME = "last_cache_time"
	}
}

class LongPreference(
	private val preferences: SharedPreferences,
	private val name: String,
	private val defaultValue: Long
) : ReadWriteProperty<Any, Long> {

	@WorkerThread
	override fun getValue(thisRef: Any, property: KProperty<*>): Long {
		return preferences.getLong(name, defaultValue)
	}

	override fun setValue(thisRef: Any, property: KProperty<*>, value: Long) {
		preferences.edit().putLong(name, value).apply()
	}
}