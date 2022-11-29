package com.jantimar.mesure_converter.controller

import com.jantimar.mesure_converter.entity.Currency
import com.jantimar.mesure_converter.entity.Item
import com.jantimar.mesure_converter.entity.Price
import com.jantimar.mesure_converter.service.ItemRepository
import com.jantimar.mesure_converter.service.MesureConverter
import com.jantimar.mesure_converter.service.PriceConverter
import com.jantimar.mesure_converter.service.PriceRepository
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam

@Controller
final class ConverterController(
    private val mesureService: MesureConverter,
    private val priceService: PriceConverter,
    private val itemRepository: ItemRepository,
    private val priceRepository: PriceRepository
) {

    @GetMapping("/")
    fun index(model: Model): String {
        model["title"] = "Converter"
        model["currencies"] = Currency.all().map { it.shortcut }
        model["items"] = historicalItems()
        return "index"
    }

    @PostMapping("/convert")
    fun convert(
        @RequestParam name: String,
        @RequestParam amount: String,
        @RequestParam price: String,
        @RequestParam currency: String,
        model: Model
    ): String {
        // Save new item
        val item = Item(name, amount, price, Currency.currency(currency))
        priceRepository.save(item.price)
        itemRepository.save(item)

        return index(model)
    }

    private fun historicalItems() = itemRepository
        .findAllByOrderByAddedAtDesc()
        .map { item ->
            val universalItem = mesureService.universalItem(item)
            val prices = Currency.all().map { priceService.convert(universalItem.price, it) }
            render(item, prices)
        }

    private fun render(item: Item, prices: List<Price>) = RenderedItem(
        item.name,
        render(item.price),
        item.amount.toString(),
        prices.map { render(it) }
    )

    data class RenderedItem(
        val name: String,
        val original: RenderedPrice,
        val amount: String,
        val prices: List<RenderedPrice>
    )

    private fun render(price: Price) = RenderedPrice(
        String.format("%.2f", price.value),
        price.currency.shortcut
    )

    data class RenderedPrice(
        val value: String,
        val shortcut: String
    )
}