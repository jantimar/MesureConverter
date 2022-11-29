package com.jantimar.mesure_converter.entity

import java.time.LocalDateTime
import javax.persistence.*

@Entity
data class Item(
    val name: String,
    val amount: Double,
    @OneToOne(cascade = [(CascadeType.ALL)]) val price: Price,
    var addedAt: LocalDateTime = LocalDateTime.now(),
    @Id @GeneratedValue var id: Long? = null) {
    constructor(name: String, amount: String, price: String, currency: Currency): this(
        name,
        amount.toDoubleOrNull() ?: 1000.0,
        Price(price.toDoubleOrNull() ?: 1.0, currency))
}