package com.snick.weather.currentWeather.domain


import com.snick.weather.core.Weather


interface WeatherDomain {


    data class Success(private val weather: Weather): WeatherDomain

    data class Fail(private val e: ApplicationExceptions):WeatherDomain
}