package com.snick.weather.currentWeather.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.snick.weather.currentWeather.domain.WeatherDomainToUiMapper
import com.snick.weather.currentWeather.domain.WeatherInteractor
import javax.inject.Inject

class WeatherViewModelFactory (
    private val interactor: WeatherInteractor,
    private val weatherDomainToUiMapper: WeatherDomainToUiMapper
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        require(modelClass == WeatherViewModel::class.java)
        return WeatherViewModel(interactor, weatherDomainToUiMapper) as T
    }


}