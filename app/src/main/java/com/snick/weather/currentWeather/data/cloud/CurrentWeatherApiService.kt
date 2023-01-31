package com.snick.weather.currentWeather.data.cloud

import org.intellij.lang.annotations.Language
import retrofit2.http.GET
import retrofit2.http.Path

interface CurrentWeatherApiService {


    @GET("weather?q={city}&appid={key}&lang={lang}&units=metric")
    fun fetchCurrentWeather(
    @Path("city") city: String,
    @Path("key") key: String = "37d34d4b86d03710f1f3318ce1223ee5",
    @Path("lang") language: String = "ru"
    ): WeatherCloud

    // BASE_URL = "https://api.openweathermap.org/data/2.5/"

}