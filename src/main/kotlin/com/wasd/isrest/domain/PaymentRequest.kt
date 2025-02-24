package com.wasd.isrest.domain

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import jakarta.validation.constraints.Min
import org.jetbrains.annotations.NotNull
import java.time.ZonedDateTime
import java.util.*

@Table
@Entity(name = "payment_requests")
class PaymentRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: UUID? = null

    @Column(name = "external_id")
    var externalId: String? = null

    @Column(name = "sum", )
    @NotNull
    @Min(value = 1, message = "Payment request sum must be positive")
    var sum: Long? = null

    @ManyToOne
    @JoinColumn(name = "currency_id", nullable = false)
    @NotNull
    var currency: Currency? = null

    @ManyToOne
    @JoinColumn(name = "balance_id", nullable = false)
    var balance: Balance? = null
    
    @ManyToOne
    @JoinColumn(name = "status", nullable = false)
    @NotNull
    var status: Reference? = null

    @Column(name = "created_at", columnDefinition = "timestamptz")
    var createdAt: ZonedDateTime? = null

    @Column(name = "updated_at", columnDefinition = "timestamptz")
    var updatedAt: ZonedDateTime? = null
}