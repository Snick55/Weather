package com.snick.weather.currentWeather.data

import android.util.Log
import com.snick.weather.currentWeather.domain.CurrentWeatherDomain
import java.lang.Exception

interface CurrentWeatherData {

   fun  map(mapper: WeatherDataToDomainMapper): CurrentWeatherDomain

   data class Success(private val weatherData: WeatherData): CurrentWeatherData {
      override fun  map(mapper: WeatherDataToDomainMapper): CurrentWeatherDomain = mapper.map(weatherData)
   }

   data class Fail(private val exception: Exception): CurrentWeatherData {

      override fun  map(mapper: WeatherDataToDomainMapper): CurrentWeatherDomain = mapper.map(exception)


   }

}