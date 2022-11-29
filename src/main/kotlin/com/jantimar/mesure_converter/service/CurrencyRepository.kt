package com.jantimar.mesure_converter.service

import com.jantimar.mesure_converter.entity.Currency
import org.springframework.data.repository.CrudRepository

interface CurrencyRepository : CrudRepository<Currency, Long>