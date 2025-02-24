package com.wasd.isrest.domain

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import jakarta.validation.constraints.Size

@Entity
@Table(name = "refs")
data class Reference(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @ManyToOne
    @JoinColumn(name = "header_id")
    var header: RefHeader? = null,

    @Column(name = "value")
    @Size(min = 1, max = 100)
    var value: String? = null,

    @Column(name = "code")
    var code: Int? = null,

    @Column(name = "active")
    var active: Boolean? = null,

    @Column(name = "name")
    var name: String? = null,
)