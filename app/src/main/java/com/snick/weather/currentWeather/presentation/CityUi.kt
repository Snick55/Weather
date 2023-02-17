package com.snick.weather.currentWeather.presentation

interface CityUi {


  data  class City(private val name: String) : CityUi

    class AddCity: CityUi

}