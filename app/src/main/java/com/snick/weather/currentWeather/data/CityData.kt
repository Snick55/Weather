package com.snick.weather.currentWeather.data

import com.snick.weather.currentWeather.data.cache.CacheCity
import com.snick.weather.currentWeather.domain.CityDomain

data class CityData(
    private val name: String
){
    fun map(): CacheCity = CacheCity(name = name)
    fun toDomainCity(): CityDomain = CityDomain(name)
}
