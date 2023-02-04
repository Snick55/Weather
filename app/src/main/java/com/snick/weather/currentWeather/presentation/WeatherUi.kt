package com.snick.weather.currentWeather.presentation

data class WeatherUi(
     val cloudiness: Int,
     val feelsLike: Double,
     val humidity: Int,
     val temp: Double,
     val pressure: Int,
     val name: String,
     val visibility: Int,
     val weatherDescription: String,
     val speed: Double
){

}

