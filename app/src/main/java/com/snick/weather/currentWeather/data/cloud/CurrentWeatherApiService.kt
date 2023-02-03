package com.snick.weather.currentWeather.data.cloud

import org.intellij.lang.annotations.Language
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CurrentWeatherApiService {


    @GET("weather?")
  suspend  fun fetchCurrentWeather(
        @Query("q") city: String,
        @Query("appid") key: String = "use api key here",
        @Query("lang") language: String = "ru",
        @Query("units") units: String = "metric"
    ): WeatherCloud
}