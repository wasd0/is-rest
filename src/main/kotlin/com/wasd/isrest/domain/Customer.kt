package com.wasd.isrest.domain

import jakarta.persistence.*
import org.hibernate.annotations.ColumnDefault
import java.time.ZonedDateTime

@Entity
@Table(name = "customers")
class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @Column(name = "telegram_id")
    var telegramId: Long? = null

    @Column(name = "blocked", nullable = false)
    @ColumnDefault("false")
    var blocked: Boolean = false

    @Column(name = "create_date")
    var createDate: ZonedDateTime? = null

    @ManyToOne
    @JoinColumn(name = "country_id", nullable = false)
    var country: Country? = null
}