package com.snick.weather.currentWeather.data.cloud

import com.google.gson.annotations.SerializedName
import com.snick.weather.core.Weather

data class WeatherCloud(
    @SerializedName("clouds")
    val clouds: Clouds = Clouds(0),
    @SerializedName("cod")
    val requestCode: Int = 0,
    @SerializedName("main")
    val main: Main = Main(0.0,0,0,0.0),
    @SerializedName("name")
    val name: String = "",
    @SerializedName("visibility")
    val visibility: Int = 0,
    @SerializedName("weather")
    val weatherDescription: List<WeatherDescription> = emptyList(),
    @SerializedName("wind")
    val wind: Wind = Wind(0.0),
    @SerializedName("message")
    val errorMessage: String = ""
) {
    fun isSuccess() = requestCode == 200

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