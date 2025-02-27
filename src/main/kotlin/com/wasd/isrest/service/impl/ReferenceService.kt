package com.wasd.isrest.service.impl

import com.wasd.isrest.domain.Reference
import com.wasd.isrest.exception.NotFoundException
import com.wasd.isrest.repository.ReferenceRepository
import com.wasd.isrest.service.RefHeaderService
import com.wasd.isrest.service.ReferenceService
import org.springframework.stereotype.Service

@Service
class ReferenceService(
    private val referenceRepository: ReferenceRepository,
    private val refHeaderService: RefHeaderService
) : ReferenceService {

    override fun getReference(refHeaderId: Int, code: Int): Reference {
        val header = refHeaderService.getEntity(refHeaderId)

        return referenceRepository.getByHeaderAndCode(header, code)
            ?: throw NotFoundException("Ref Code: $code")
    }

    override fun getReferences(refHeaderId: Int, codes: List<Int>): List<Reference> {
        val header = refHeaderService.getEntity(refHeaderId)
        return referenceRepository.findAllByHeaderAndCodeIn(header, codes)
    }


}