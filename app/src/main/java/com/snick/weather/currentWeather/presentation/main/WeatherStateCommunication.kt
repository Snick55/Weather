package com.snick.weather.currentWeather.presentation.main

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.snick.weather.currentWeather.presentation.CurrentWeatherUi
import javax.inject.Inject

interface WeatherStateCommunication {


    fun show(weatherUi : CurrentWeatherUi)

    fun observe(owner: LifecycleOwner, observer: Observer<CurrentWeatherUi>)


    class Base @Inject constructor(): WeatherStateCommunication{

        private val state = MutableLiveData<CurrentWeatherUi>(CurrentWeatherUi.Loading())

        override fun show(weatherUi: CurrentWeatherUi) {
            state.value = weatherUi
        }

        override fun observe(owner: LifecycleOwner, observer: Observer<CurrentWeatherUi>) {
           state.observe(owner, observer)
        }
    }

}