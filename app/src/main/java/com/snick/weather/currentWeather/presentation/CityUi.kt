package com.snick.weather.currentWeather.presentation

import android.widget.Button

interface CityUi {

  fun show(button: Button) = Unit

  data  class City(private val name: String) : CityUi{
    override fun show(button: Button) {
      button.text = name
    }
  }

    class AddCity: CityUi

}