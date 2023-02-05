package com.snick.weather.currentWeather.data.cloud

import android.util.Log
import com.snick.weather.currentWeather.domain.WeatherApiException
import com.snick.weather.currentWeather.data.CurrentWeatherData
import java.lang.Exception

interface CloudWeatherDataSource {

    suspend fun fetchCurrentWeather(city: String): CurrentWeatherData


    class Base(
        private val apiService: CurrentWeatherApiService,
    ) : CloudWeatherDataSource {
        override suspend fun fetchCurrentWeather(city: String): CurrentWeatherData {
            return try {
                val result = apiService.fetchCurrentWeather(city)
                if (result.isSuccess()) {
                    CurrentWeatherData.Success(result.map())
                } else CurrentWeatherData.Fail(WeatherApiException(result.errorMessage))
            } catch (e: Exception) {
                CurrentWeatherData.Fail(e)
            }
        }
    }
}