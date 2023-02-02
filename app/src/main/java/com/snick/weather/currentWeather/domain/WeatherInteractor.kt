package com.snick.weather.currentWeather.domain

import com.snick.weather.currentWeather.data.CurrentWeatherRepository

interface WeatherInteractor {

    suspend fun fetchWeather(city: String): WeatherDomain


    class Base(
        private val repository: CurrentWeatherRepository
    ) : WeatherInteractor {

        override suspend fun fetchWeather(city: String): WeatherDomain {
            return repository.fetchCurrentWeather(city)
        }
    }
}