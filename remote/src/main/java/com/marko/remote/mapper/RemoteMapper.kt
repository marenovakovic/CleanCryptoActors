package com.marko.remote.mapper

interface RemoteMapper<D, R> {

	fun mapFromData(data: D): R

	fun mapFromData(datas: List<D>): List<R>

	fun mapToData(remote: R): D

	fun mapToData(remotes: List<R>): List<D>
}