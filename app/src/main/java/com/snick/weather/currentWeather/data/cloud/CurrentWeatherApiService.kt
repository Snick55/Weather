package com.snick.weather.currentWeather.data.cloud

import org.intellij.lang.annotations.Language
import retrofit2.http.GET
import retrofit2.http.Path

interface CurrentWeatherApiService {


    @GET("weather?q={city}&appid={key}&lang={lang}&units=metric")
    fun fetchCurrentWeather(
    @Path("city") city: String,
    @Path("key") key: String = "use api key here",
    @Path("lang") language: String = "ru"
    ): WeatherCloud

    // BASE_URL = "https://api.openweathermap.org/data/2.5/"

}