package com.jantimar.mesure_converter.service

import com.jantimar.mesure_converter.entity.Price
import org.springframework.data.repository.CrudRepository

interface PriceRepository : CrudRepository<Price, Long>