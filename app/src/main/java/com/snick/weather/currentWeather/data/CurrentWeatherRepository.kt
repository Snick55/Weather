package com.snick.weather.currentWeather.data

import com.snick.weather.currentWeather.data.cloud.CloudWeatherDataSource
import com.snick.weather.currentWeather.domain.CurrentWeatherDomain
import javax.inject.Inject

interface CurrentWeatherRepository {

    suspend fun fetchCurrentWeather(city: String): CurrentWeatherDomain

    class Base @Inject constructor (
        private val cloudDataSource: CloudWeatherDataSource,
        private val mapper: WeatherDataToDomainMapper
    ) : CurrentWeatherRepository {

        override suspend fun fetchCurrentWeather(city: String): CurrentWeatherDomain {
            val weatherData = cloudDataSource.fetchCurrentWeather(city)
            return weatherData.map(mapper)
        }
    }

}