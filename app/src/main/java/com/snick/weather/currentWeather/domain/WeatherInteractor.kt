package com.snick.weather.currentWeather.domain

import com.snick.weather.currentWeather.data.CurrentWeatherRepository
import javax.inject.Inject

interface WeatherInteractor {

    suspend fun fetchWeather(city: String): CurrentWeatherDomain


    class Base @Inject constructor (
        private val repository: CurrentWeatherRepository
    ) : WeatherInteractor {

        override suspend fun fetchWeather(city: String): CurrentWeatherDomain {
            return repository.fetchCurrentWeather(city)
        }
    }
}