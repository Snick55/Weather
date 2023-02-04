package com.snick.weather.currentWeather.presentation

import android.widget.TextView
import com.snick.weather.R
import com.snick.weather.core.ResourceManager

interface CurrentWeatherUi {

    fun render(
        city: TextView,
        weatherDescription: TextView,
        temp: TextView,
        feelsLike: TextView,
        humidity: TextView,
        cloudiness: TextView,
        pressure: TextView,
        visibility: TextView,
        speed: TextView
    ) = Unit

    fun handleError(errorTextView: TextView) = Unit


    class Loading : CurrentWeatherUi

    data class Success(
        private val weatherUi: WeatherUi,
        private val resourceManager: ResourceManager
    ) : CurrentWeatherUi {
        override fun render(
            city: TextView,
            weatherDescription: TextView,
            temp: TextView,
            feelsLike: TextView,
            humidity: TextView,
            cloudiness: TextView,
            pressure: TextView,
            visibility: TextView,
            speed: TextView
        ) {
            val press = weatherUi.pressure * 0.75
            city.text = weatherUi.name
            weatherDescription.text =   weatherUi.weatherDescription
            temp.text = resourceManager.getString(R.string.temp, weatherUi.temp.toString())
            feelsLike.text = resourceManager.getString(R.string.feel_like,weatherUi.feelsLike.toString())
            humidity.text = resourceManager.getString(R.string.humidity,weatherUi.humidity.toString()+"%")
            cloudiness.text =resourceManager.getString(R.string.cloudiness,weatherUi.cloudiness.toString()+"%")
            pressure.text = resourceManager.getString(R.string.pressure,press.toString())
            visibility.text = resourceManager.getString(R.string.visibility,weatherUi.visibility.toString()+"%")
            speed.text = resourceManager.getString(R.string.speed,weatherUi.speed.toString())
        }
    }

    data class Fail(private val errorMessage: String) : CurrentWeatherUi {
        override fun handleError(errorTextView: TextView) {
            errorTextView.text = errorMessage
        }
    }

}