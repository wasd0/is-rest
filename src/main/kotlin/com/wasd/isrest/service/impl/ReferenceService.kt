package com.wasd.isrest.service.impl

import com.wasd.isrest.domain.Reference
import com.wasd.isrest.exception.NotFoundException
import com.wasd.isrest.repository.RefHeaderRepository
import com.wasd.isrest.repository.ReferenceRepository
import com.wasd.isrest.service.ReferenceService
import org.springframework.stereotype.Service

@Service
class ReferenceService(
    private val referenceRepository: ReferenceRepository,
    private val refHeaderRepository: RefHeaderRepository
) : ReferenceService {

    override fun getReference(refHeaderId: Int, codeInteger: Int): Reference {
        val header =
            refHeaderRepository.findById(refHeaderId).orElseThrow {
                throw NotFoundException("RefHeaderId: $refHeaderId")
            }

        return referenceRepository.getByHeaderAndCode(header, codeInteger)
            ?: throw NotFoundException("Ref Code: $codeInteger")
    }


}