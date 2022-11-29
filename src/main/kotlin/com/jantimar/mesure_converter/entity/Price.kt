package com.jantimar.mesure_converter.entity

import javax.persistence.*

@Entity
data class Price(
    val value: Double,
    @ManyToOne val currency: Currency,
    @Id @GeneratedValue var id: Long? = null)