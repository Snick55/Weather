package com.snick.weather.currentWeather.data.mappers

import com.snick.weather.currentWeather.data.WeatherData
import com.snick.weather.currentWeather.domain.NoInternetConnectionException
import com.snick.weather.currentWeather.domain.ServiceUnavailableException
import com.snick.weather.currentWeather.domain.WeatherApiException
import com.snick.weather.currentWeather.domain.CurrentWeatherDomain
import retrofit2.HttpException
import java.lang.Exception
import java.net.UnknownHostException
import javax.inject.Inject

interface WeatherDataToDomainMapper {

    fun map(weatherData: WeatherData): CurrentWeatherDomain
    fun map(exception: Exception): CurrentWeatherDomain


    class Base @Inject constructor () : WeatherDataToDomainMapper {


        override fun map(weatherData: WeatherData): CurrentWeatherDomain {
            return CurrentWeatherDomain.Success(weatherData.toDomain())
        }

        override fun map(exception: Exception): CurrentWeatherDomain {
            val applicationException = when (exception) {
                is HttpException -> ServiceUnavailableException("city not found")
                is UnknownHostException -> NoInternetConnectionException()
                is WeatherApiException -> ServiceUnavailableException(exception.message)
                else -> ServiceUnavailableException("something went wrong")
            }
            return CurrentWeatherDomain.Fail(applicationException)
        }
    }


}