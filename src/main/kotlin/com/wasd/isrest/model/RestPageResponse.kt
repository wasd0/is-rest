package com.wasd.isrest.model

import org.springframework.data.domain.Page

class RestPageResponse<T>(page: Page<T>) {
    val data: List<T> = page.content
    val page = page.number
    val size = page.size
    val total = page.totalElements
}