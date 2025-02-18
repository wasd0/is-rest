package com.wasd.isrest.domain

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size
import org.hibernate.annotations.ColumnDefault

@Entity
@Table(name = "ref_headers")
class RefHeader {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private var id: Int? = null
    
    @Column(name = "name")
    @NotBlank
    @Size(min = 1, max = 100)
    private var name: String? = null
    
    @Column(name = "is_active")
    @ColumnDefault("false")
    var isActive: Boolean? = null
}