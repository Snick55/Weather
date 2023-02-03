package com.snick.weather.currentWeather.presentation

import android.widget.TextView
import com.snick.weather.core.Weather

interface WeatherUi {

    fun render(textView: TextView) = Unit



    class Loading : WeatherUi
    data class Success(private val weather: Weather) : WeatherUi{
        override fun render(textView: TextView) {
            textView.text = "done"
        }
    }
    data class Fail(private val errorMessage: String) : WeatherUi{
        override fun render(textView: TextView) {
            textView.text = errorMessage
        }
    }

}