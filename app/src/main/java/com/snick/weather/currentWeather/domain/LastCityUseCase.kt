package com.snick.weather.currentWeather.domain

import com.snick.weather.currentWeather.data.CurrentWeatherRepository
import javax.inject.Inject

interface LastCityUseCase {

    fun execute(): String

    fun save(cityName: String)

    class Base @Inject constructor(private val repository: CurrentWeatherRepository): LastCityUseCase{
        override fun execute(): String {
           return repository.getLastCity()
        }

        override fun save(cityName: String) {
            repository.saveLastCity(cityName)
        }
    }

}