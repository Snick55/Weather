package com.snick.weather.currentWeather.domain

import com.snick.weather.currentWeather.data.CityData
import com.snick.weather.currentWeather.presentation.CityUi

data class CityDomain(
    private val name: String
){
    fun toData() = CityData(name)

    fun toUi() = CityUi.City(name)
}
