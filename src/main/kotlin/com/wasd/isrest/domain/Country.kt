package com.wasd.isrest.domain

import jakarta.persistence.*

@Entity
@Table(name = "countries", uniqueConstraints = [UniqueConstraint(columnNames = ["code", "iso"])])
data class Country(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null,

    @Column(name = "code", nullable = false)
    var code: Int? = null,

    @Column(name = "iso", nullable = false)
    var iso: String? = null,

    @Column(name = "name")
    var name: String? = null
)