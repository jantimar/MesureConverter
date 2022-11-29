package com.jantimar.mesure_converter.controller

import com.jantimar.mesure_converter.entity.Item
import com.jantimar.mesure_converter.service.ItemRepository
import org.springframework.web.bind.annotation.*

@RestController
final class ConverterRestController(
    private val repository: ItemRepository) {

    @GetMapping("/all")
    fun index(): List<Item> = repository
        .findAll()
        .toList()

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long) {
        repository.deleteById(id)
    }
}