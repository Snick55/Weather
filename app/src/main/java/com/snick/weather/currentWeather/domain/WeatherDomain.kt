package com.snick.weather.currentWeather.domain

import com.snick.weather.currentWeather.presentation.WeatherUi

data class WeatherDomain(
    private val cloudiness: Int,
    private val feelsLike: Double,
    private val humidity: Int,
    private val temp: Double,
    private val pressure: Int,
    private val name: String,
    private val visibility: Int,
    private val weatherDescription: String,
    private val speed: Double
) {
    fun toUi() = WeatherUi(
        cloudiness,
        feelsLike,
        humidity,
        temp,
        pressure,
        name,
        visibility,
        weatherDescription,
        speed
    )
}
