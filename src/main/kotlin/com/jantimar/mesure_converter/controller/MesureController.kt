package com.jantimar.mesure_converter.controller

import com.jantimar.mesure_converter.MesureConverter
import com.jantimar.mesure_converter.PriceConverter
import com.jantimar.mesure_converter.entity.Currency
import com.jantimar.mesure_converter.entity.Item
import com.jantimar.mesure_converter.entity.Price
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam

@Controller
class MesureController(val mesureService: MesureConverter, val priceService: PriceConverter) {

    @GetMapping("/")
    fun index(model: Model): String {
        model["title"] = "Converter"
        return "index"
    }

    @PostMapping("/convert")
    fun convert(
        @RequestParam amount: String,
        @RequestParam("price") originalPrice: String,
        @RequestParam("fromCurrency") fromCurrencyShortcut: String,
        @RequestParam("toCurrency") toCurrencyShortcut: String,
        model: Model): String {

        val item = Item(amount, originalPrice, Currency.currency(fromCurrencyShortcut))
        val universalItem = mesureService.universalItem(item)
        val czechPrice = priceService.convert(universalItem.price, Currency.currency(fromCurrencyShortcut))

        model["title"] = "Converted"
        model["item"] = render(item, czechPrice)
        return  "converted_item"
    }

    fun render(item: Item, unitItemPrice: Price) = RenderedItem(
        item.price.value.toString(),
        item.price.currency.shortcut,
        item.amount.toString(),
        unitItemPrice.value.toString(),
        unitItemPrice.currency.shortcut
    )

    data class RenderedItem(
        val originalPrice: String,
        val originalCurrency: String,
        val amount: String,
        val unitPrice: String,
        val unitCurrency: String)
}