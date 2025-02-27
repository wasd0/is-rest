package com.wasd.isrest.service.impl

import com.wasd.isrest.domain.RefHeader
import com.wasd.isrest.repository.RefHeaderRepository
import com.wasd.isrest.service.AbstractBaseEntityService
import com.wasd.isrest.service.RefHeaderService
import org.springframework.stereotype.Service

@Service
class RefHeaderServiceImpl(refHeaderRepository: RefHeaderRepository) : RefHeaderService,
    AbstractBaseEntityService<RefHeader, Int>(refHeaderRepository) 