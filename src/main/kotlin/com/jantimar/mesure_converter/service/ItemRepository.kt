package com.jantimar.mesure_converter.service

import com.jantimar.mesure_converter.entity.Item
import org.springframework.data.repository.CrudRepository

interface ItemRepository : CrudRepository<Item, Long> {
    fun findAllByOrderByAddedAtDesc(): Iterable<Item>
}