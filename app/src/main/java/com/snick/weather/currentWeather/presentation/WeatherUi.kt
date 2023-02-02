package com.snick.weather.currentWeather.presentation

import com.snick.weather.core.Weather

interface WeatherUi {


    class Loading : WeatherUi
    data class Success(private val weather: Weather) : WeatherUi
    data class Fail(private val errorMessage: String) : WeatherUi

}