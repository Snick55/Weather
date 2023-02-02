package com.snick.weather.currentWeather.domain


import com.snick.weather.core.Weather


interface WeatherDomain {

    fun <T> map(mapper: WeatherDomainToUiMapper): T

    data class Success(private val weather: Weather): WeatherDomain{
        override fun <T> map(mapper: WeatherDomainToUiMapper): T {
           return mapper.map(weather) as T
        }
    }

    data class Fail(private val e: ApplicationExceptions):WeatherDomain{
        override fun <T> map(mapper: WeatherDomainToUiMapper): T {
           return mapper.map(e) as T
        }
    }
}