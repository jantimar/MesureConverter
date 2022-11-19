package com.jantimar.mesure_converter.entity

data class Currency(val name: String, val shortcut: String, val toCzechCrown: Double) {
    companion object {
        val czechCrown = Currency("Czech crown", "CZK", 1.0)
        val malaysianRinggit = Currency("Malaysian ringgit", "MYR", 5.2)
        fun currency(shortcut: String): Currency {
            return when (shortcut.uppercase()) {
                "CZK" -> Currency.czechCrown
                "MYR" -> Currency.malaysianRinggit
                else -> Currency.czechCrown
            }
        }
    }
}