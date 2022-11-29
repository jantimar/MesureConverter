package com.jantimar.mesure_converter.service

import com.jantimar.mesure_converter.entity.Item
import com.jantimar.mesure_converter.entity.Price
import org.springframework.stereotype.Service

@Service
class MesureConverter {
    fun universalItem(item: Item): Item {
        val multiple = item.amount / 1000
        val unitPrice = item.price.value / multiple

        return Item(item.name,1000.0, Price(unitPrice, item.price.currency))
    }
}