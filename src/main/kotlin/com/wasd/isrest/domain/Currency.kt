package com.wasd.isrest.domain

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.jetbrains.annotations.NotNull

@Entity
@Table(name = "currencies")
class Currency {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int = 0
    
    @Column(name = "code", nullable = false, columnDefinition = "VARCHAR(3)")
    @NotNull
    var code: String? = null
    
    @Column(name =  "dimension", nullable = false)
    @NotNull
    var dimension: Int = 2
    
    @Column(name = "min_value")
    var minValue: Long? = null
    
    @Column(name = "max_value")
    var maxValue: Long? = null
}