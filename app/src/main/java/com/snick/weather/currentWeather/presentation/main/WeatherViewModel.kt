package com.snick.weather.currentWeather.presentation.main

import androidx.lifecycle.*
import com.snick.weather.currentWeather.domain.WeatherDomainToUiMapper
import com.snick.weather.currentWeather.domain.WeatherInteractor
import com.snick.weather.currentWeather.presentation.CurrentWeatherUi
import kotlinx.coroutines.launch
import javax.inject.Inject


class WeatherViewModel @Inject constructor (
    private val interactor: WeatherInteractor,
    private val mapper: WeatherDomainToUiMapper,
    private val communication: WeatherStateCommunication
): ViewModel() {


    fun fetchWeather(city: String) = viewModelScope.launch {
        val weatherDomain = interactor.fetchWeather(city)
        communication.show(weatherDomain.map(mapper))
    }


    fun observeState(owner: LifecycleOwner,observer: Observer<CurrentWeatherUi>){
        communication.observe(owner, observer)
    }

}