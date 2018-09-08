package com.marko.cache.mapper

interface CacheMapper<D, C> {

	fun mapFromData(data: D): C

	fun mapFromData(datas: List<D>): List<C>

	fun mapToData(cache: C): D

	fun mapToData(caches: List<C>): List<D>
}