package com.snick.weather.currentWeather.domain

import android.util.Log
import com.snick.weather.R
import com.snick.weather.core.ResourceManager
import com.snick.weather.core.Weather
import com.snick.weather.currentWeather.presentation.WeatherUi

interface WeatherDomainToUiMapper {

    fun map(weather: Weather): WeatherUi
    fun map(e: ApplicationExceptions): WeatherUi


    class Base(
        private val resourceManager: ResourceManager
    ) : WeatherDomainToUiMapper {
        override fun map(weather: Weather): WeatherUi {
            return WeatherUi.Success(weather)
        }

        override fun map(e: ApplicationExceptions): WeatherUi {
            Log.d("TAG","ERROR IS $e")
            val errorMessage = when (e) {
                is NoInternetConnectionException -> resourceManager.getString(R.string.no_no_internet_connection)
                is ServiceUnavailableException -> e.message
                else -> resourceManager.getString(R.string.something_went_wrong)
            }
            return WeatherUi.Fail(errorMessage)
        }
    }


}