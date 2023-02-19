package com.snick.weather.currentWeather.presentation

import android.widget.Button
import com.snick.weather.currentWeather.domain.CityDomain

interface CityUi {

  fun show(button: Button) = Unit


  data  class City( val name: String) : CityUi{
    override fun show(button: Button) {
      button.text = name
    }

    fun toDomain() = CityDomain(name)
  }

    class AddCity: CityUi

}