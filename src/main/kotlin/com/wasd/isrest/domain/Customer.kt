package com.wasd.isrest.domain

import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.OneToMany
import jakarta.persistence.Table
import org.hibernate.annotations.ColumnDefault
import java.time.ZonedDateTime


@Entity
@Table(name = "customers")
data class Customer (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @Column(name = "telegram_id")
    var telegramId: Long? = null,

    @Column(name = "blocked", nullable = false)
    @ColumnDefault("false")
    var blocked: Boolean = false,

    @Column(name = "create_date")
    var createDate: ZonedDateTime? = null,

    @ManyToOne
    @JoinColumn(name = "country_id", nullable = false)
    var country: Country? = null,
    
    @OneToMany(mappedBy = "customer", cascade = [CascadeType.DETACH])
    var balances: List<Balance>  = mutableListOf()
)