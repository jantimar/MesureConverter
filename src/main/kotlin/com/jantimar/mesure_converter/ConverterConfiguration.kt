package com.jantimar.mesure_converter

import com.jantimar.mesure_converter.entity.Currency
import com.jantimar.mesure_converter.service.CurrencyRepository
import org.springframework.boot.ApplicationRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ConverterConfiguration {

    @Bean
    fun databaseInitializer(currencyRepository: CurrencyRepository) = ApplicationRunner {
        Currency.all().forEach {
            currencyRepository.save(it)
        }
    }
}