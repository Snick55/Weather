package com.snick.weather.core



data class Weather(
   private val cloudiness: Int,
   private val feelsLike: Double,
   private val humidity: Int,
   private val temp: Double,
   private val pressure: Int,
   private val name: String,
   private val visibility: Int,
   private val weatherDescription: String,
   private val speed: Double
)
