package com.snick.weather.currentWeather.data.cloud

import com.google.gson.annotations.SerializedName
import com.snick.weather.core.Weather

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
    val weatherDescription: List<WeatherDescription>,
    @SerializedName("wind")
    val wind: Wind,
    @SerializedName("message")
    val errorMessage: String
) {
    fun isSuccess() = RequestCode == 200

    fun map() = Weather(
        clouds.cloudiness,
        main.feelsLike,
        main.humidity,
        main.temp,
        main.pressure,
        name,
        visibility,
        weatherDescription[0].description,
        wind.speed,
    )
}

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

data class WeatherDescription(
    @SerializedName("description")
    val description: String
)

data class Wind(
    @SerializedName("speed")
    val speed: Double
)