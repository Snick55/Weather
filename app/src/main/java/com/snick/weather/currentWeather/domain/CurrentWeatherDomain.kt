package com.snick.weather.currentWeather.domain


import com.snick.weather.currentWeather.data.WeatherData


interface CurrentWeatherDomain {

    fun <T> map(mapper: WeatherDomainToUiMapper): T

    data class Success(private val weather: WeatherDomain): CurrentWeatherDomain{
        override fun <T> map(mapper: WeatherDomainToUiMapper): T {
           return mapper.map(weather) as T
        }
    }

    data class Fail(private val e: ApplicationExceptions):CurrentWeatherDomain{
        override fun <T> map(mapper: WeatherDomainToUiMapper): T {
           return mapper.map(e) as T
        }
    }
}