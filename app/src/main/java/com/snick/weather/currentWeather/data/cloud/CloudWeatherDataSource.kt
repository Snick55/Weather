package com.snick.weather.currentWeather.data.cloud

import com.snick.weather.currentWeather.domain.WeatherApiException
import com.snick.weather.currentWeather.data.WeatherData
import java.lang.Exception

interface CloudWeatherDataSource {

    suspend fun fetchCurrentWeather(city: String): WeatherData


    class Base(
        private val apiService: CurrentWeatherApiService,
    ) : CloudWeatherDataSource {
        override suspend fun fetchCurrentWeather(city: String): WeatherData {
            return try {
                val result = apiService.fetchCurrentWeather(city)
                if (result.isSuccess()) {
                    WeatherData.Success(result.map())
                } else WeatherData.Fail(WeatherApiException(result.errorMessage))
            } catch (e: Exception) {
                WeatherData.Fail(e)
            }
        }
    }
}