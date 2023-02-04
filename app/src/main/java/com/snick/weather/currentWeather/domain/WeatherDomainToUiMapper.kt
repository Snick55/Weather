package com.snick.weather.currentWeather.domain

import android.util.Log
import com.snick.weather.R
import com.snick.weather.core.ResourceManager
import com.snick.weather.currentWeather.data.WeatherData
import com.snick.weather.currentWeather.presentation.CurrentWeatherUi

interface WeatherDomainToUiMapper {

    fun map(weather: WeatherDomain): CurrentWeatherUi
    fun map(e: ApplicationExceptions): CurrentWeatherUi


    class Base(
        private val resourceManager: ResourceManager
    ) : WeatherDomainToUiMapper {

        override fun map(weather: WeatherDomain): CurrentWeatherUi {
            return CurrentWeatherUi.Success(weather.toUi(),resourceManager)
        }

        override fun map(e: ApplicationExceptions): CurrentWeatherUi {
            val errorMessage = when (e) {
                is NoInternetConnectionException -> resourceManager.getString(R.string.no_no_internet_connection)
                is ServiceUnavailableException -> e.message
                else -> resourceManager.getString(R.string.something_went_wrong)
            }
            return CurrentWeatherUi.Fail(errorMessage)
        }
    }


}