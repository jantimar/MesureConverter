package com.jantimar.mesure_converter.entity

import javax.persistence.Entity
import javax.persistence.Id

@Entity
data class Currency(
    val name: String,
    @Id val shortcut: String,
    val toCzechCrown: Double) {
    companion object {
        private val czechCrown = Currency("Czech crown", "CZK", 1.0)
        private val malaysianRinggit = Currency("Malaysian ringgit", "MYR", 5.2)
        private val thaiBath = Currency("Thai Bath", "THB", 0.65)
        private val euro = Currency("Euro", "EUR", 24.38)

        fun all() = listOf(
            czechCrown,
            malaysianRinggit,
            thaiBath,
            euro
        )

        fun currency(shortcut: String) =
            all().firstOrNull { it.shortcut == shortcut.uppercase() } ?: czechCrown
    }
}