package com.snick.weather.currentWeather.data.cloud

import com.google.gson.annotations.SerializedName

data class WeatherCloud(
    @SerializedName("clouds")
    val clouds: Clouds,
    @SerializedName("cod")
    val RequestCode: Int,
    @SerializedName("main")
    val main: Main,
    @SerializedName("name")
    val name: String,
    @SerializedName("visibility")
    val visibility: Int,
    @SerializedName("weather")
    val weather: List<Weather>,
    @SerializedName("wind")
    val wind: Wind
)

data class Clouds(
    @SerializedName("all")
    val cloudiness: Int
)


data class Main(
    @SerializedName("feels_like")
    val feelsLike: Double,
    @SerializedName("humidity")
    val humidity: Int,
    @SerializedName("pressure")
    val pressure: Int,
    @SerializedName("temp")
    val temp: Double
)

data class Weather(
    @SerializedName("description")
    val description: String
)

data class Wind(
    @SerializedName("speed")
    val speed: Double
)