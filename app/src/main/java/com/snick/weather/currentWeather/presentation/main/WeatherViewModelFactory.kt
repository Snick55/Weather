package com.snick.weather.currentWeather.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.snick.weather.currentWeather.domain.LastCityUseCase
import com.snick.weather.currentWeather.domain.WeatherDomainToUiMapper
import com.snick.weather.currentWeather.domain.WeatherInteractor
import com.snick.weather.currentWeather.presentation.MainViewModel

class WeatherViewModelFactory (
    private val interactor: WeatherInteractor,
    private val weatherDomainToUiMapper: WeatherDomainToUiMapper,
    private val communication: WeatherStateCommunication,
    private val useCase: LastCityUseCase
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        require(modelClass == WeatherViewModel::class.java)
        return WeatherViewModel(interactor, weatherDomainToUiMapper,communication,useCase) as T
    }


}


class MainViewModelFactory(
    private val useCase: LastCityUseCase
): ViewModelProvider.Factory{

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        require(modelClass == MainViewModel::class.java)
        return MainViewModel(useCase) as T
    }

}