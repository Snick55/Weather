package com.snick.weather.currentWeather.data.cloud

import com.snick.weather.currentWeather.data.WeatherData
import java.lang.Exception

interface CloudWeatherDataSource {

    suspend fun fetchCurrentWeather(city: String): WeatherData


    class Base(private val apiService: CurrentWeatherApiService): CloudWeatherDataSource{
        override suspend fun fetchCurrentWeather(city: String): WeatherData {
            return try {
                val result = apiService.fetchCurrentWeather(city)
                WeatherData.Success(result.map)

            }catch (e: Exception){
                WeatherData.Fail(e)
            }
        }
    }

}