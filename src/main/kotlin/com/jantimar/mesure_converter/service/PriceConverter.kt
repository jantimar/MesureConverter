package com.jantimar.mesure_converter.service

import com.jantimar.mesure_converter.entity.Currency
import com.jantimar.mesure_converter.entity.Price
import org.springframework.stereotype.Service

@Service
class PriceConverter {
    fun convert(price: Price, currency: Currency) = Price(
        price.value * price.currency.toCzechCrown / currency.toCzechCrown,
        currency
    )
}