package com.jantimar.mesure_converter

import org.springframework.stereotype.Service

@Service
class MesureConverter {
    fun universalItem(item: Item): Item {
        val multiple = item.amount / 1000
        val unitPrice = item.price.value / multiple

        return Item(1000.0, Price(unitPrice, item.price.currency))
    }
}