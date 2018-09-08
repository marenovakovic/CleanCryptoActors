package com.marko.data.mapper

interface DataMapper<E, D> {

	fun mapFromEntity(entity: E): D

	fun mapFromEntity(entities: List<E>): List<D>

	fun mapToEntity(data: D): E

	fun mapToEntity(datas: List<D>): List<E>
}