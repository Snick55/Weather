package com.snick.weather.currentWeather.domain

import com.snick.weather.currentWeather.data.CurrentWeatherRepository

interface WeatherInteractor {

    suspend fun fetchWeather(city: String): CurrentWeatherDomain


    class Base(
        private val repository: CurrentWeatherRepository
    ) : WeatherInteractor {

        override suspend fun fetchWeather(city: String): CurrentWeatherDomain {
            return repository.fetchCurrentWeather(city)
        }
    }
}