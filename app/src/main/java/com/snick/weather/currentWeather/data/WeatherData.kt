package com.snick.weather.currentWeather.data

import com.snick.weather.core.Weather
import com.snick.weather.currentWeather.domain.WeatherDomain
import java.lang.Exception

interface WeatherData {

   fun  map(mapper: WeatherDataToDomainMapper): WeatherDomain

   data class Success(private val weather: Weather): WeatherData{
      override fun  map(mapper: WeatherDataToDomainMapper): WeatherDomain = mapper.map(weather)
   }

   data class Fail(private val exception: Exception): WeatherData{
      override fun  map(mapper: WeatherDataToDomainMapper): WeatherDomain = mapper.map(exception)


   }

}