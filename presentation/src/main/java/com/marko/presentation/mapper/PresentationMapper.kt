package com.marko.presentation.mapper

interface PresentationMapper<E, P> {

	fun mapFromEntity(entity: E): P

	fun mapFromEntity(entities: List<E>): List<P>

	fun mapToEntity(presentation: P): E

	fun mapToEntity(presentations: List<P>): List<E>
}