package com.jantimar.mesure_converter.service

import com.jantimar.mesure_converter.entity.Item
import org.springframework.stereotype.Service

@Service
final class ItemService(
    private val itemRepository: ItemRepository,
    private val priceRepository: PriceRepository) {

    fun all(): List<Item> = itemRepository
        .findAllByOrderByAddedAtDesc()
        .toList()

    fun save(item: Item) {
        priceRepository.save(item.price)
        itemRepository.save(item)
    }
}