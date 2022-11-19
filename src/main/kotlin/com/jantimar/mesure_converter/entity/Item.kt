package com.jantimar.mesure_converter.entity

data class Item(val amount: Double, val price: Price) {
    constructor(amount: String, price: String, currency: Currency): this(
        amount.toDoubleOrNull() ?: 1000.0,
        Price(price.toDoubleOrNull() ?: 1.0, currency))
}