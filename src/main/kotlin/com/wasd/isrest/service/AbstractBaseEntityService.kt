package com.wasd.isrest.service

import com.wasd.isrest.exception.NotFoundException
import org.springframework.data.jpa.repository.JpaRepository

abstract class AbstractBaseEntityService<ENTITY : Any, ID : Any> protected constructor(
    private val entityRepository: JpaRepository<ENTITY, ID>,
) : BaseEntityService<ENTITY, ID> {

    override fun getEntity(id: ID): ENTITY {
        return findEntity(id) ?: throw NotFoundException("Entity with id '$id' not found")
    }

    override fun findEntity(id: ID): ENTITY? {
        return entityRepository.findById(id).orElse(null)
    }

    override fun save(entity: ENTITY): ENTITY {
        return entityRepository.save(entity)
    }

    override fun saveAll(entities: List<ENTITY>) {
        entityRepository.saveAll(entities)
    }
}