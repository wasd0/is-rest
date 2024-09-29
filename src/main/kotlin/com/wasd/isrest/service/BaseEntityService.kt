package com.wasd.isrest.service

interface BaseEntityService<ENTITY, ID> {

    fun getEntity(id: ID): ENTITY

    fun findEntity(id: ID): ENTITY?

    fun save(entity: ENTITY): ENTITY
}