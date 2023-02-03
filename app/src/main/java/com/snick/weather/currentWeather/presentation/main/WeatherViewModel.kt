package com.snick.weather.currentWeather.presentation.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.snick.weather.currentWeather.domain.WeatherDomainToUiMapper
import com.snick.weather.currentWeather.domain.WeatherInteractor
import com.snick.weather.currentWeather.presentation.WeatherUi
import kotlinx.coroutines.launch

class WeatherViewModel(
    private val interactor: WeatherInteractor,
    private val mapper: WeatherDomainToUiMapper
): ViewModel() {

    private val _state = MutableLiveData<WeatherUi>(WeatherUi.Loading())
    val state: LiveData<WeatherUi> = _state

    fun fetchWeather(city: String) = viewModelScope.launch {
        val res = interactor.fetchWeather(city)
        _state.value = res.map(mapper)
    }

}