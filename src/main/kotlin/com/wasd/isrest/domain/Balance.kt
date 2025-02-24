package com.wasd.isrest.domain

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import org.jetbrains.annotations.NotNull

@Entity
@Table(name = "balances")
data class Balance (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    @NotNull
    var customer: Customer? = null,
    
    @Column(name = "sum")
    @NotNull
    var sum: Long = 0,
    
    @ManyToOne
    @JoinColumn(name = "currency_id", nullable = false)
    @NotNull
    var currency: Currency? = null
)