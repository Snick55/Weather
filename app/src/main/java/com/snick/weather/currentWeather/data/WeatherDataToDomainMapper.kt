package com.snick.weather.currentWeather.data

import com.snick.weather.core.Weather
import com.snick.weather.currentWeather.domain.WeatherDomain
import java.lang.Exception
import java.net.UnknownHostException

interface WeatherDataToDomainMapper {

    fun  map(weather: Weather): WeatherDomain
    fun  map(exception: Exception): WeatherDomain


    class Base: WeatherDataToDomainMapper{


        override fun map(weather: Weather): WeatherDomain {
          return  WeatherDomain.Success(weather)
        }

        override fun map(exception: Exception): WeatherDomain {
          val applicationException =  when (exception){
                is UnknownHostException ->  NoInternetConnectionException()
                isWeatherApiException ->  ServiceUnavailableException(exception.message)
              else -> {// TODO:
              }

          }
            return WeatherDomain.Fail(applicationException)
        }
    }




}