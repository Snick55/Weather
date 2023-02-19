package com.snick.weather.currentWeather.presentation.main

import android.util.Log
import androidx.lifecycle.*
import com.snick.weather.currentWeather.domain.WeatherDomainToUiMapper
import com.snick.weather.currentWeather.domain.WeatherInteractor
import com.snick.weather.currentWeather.presentation.CityUi
import com.snick.weather.currentWeather.presentation.CurrentWeatherUi
import kotlinx.coroutines.launch
import javax.inject.Inject


class WeatherViewModel @Inject constructor (
    private val interactor: WeatherInteractor,
    private val mapper: WeatherDomainToUiMapper,
    private val communication: WeatherStateCommunication
): ViewModel() {

    private val _cities: MutableLiveData<List<CityUi>> = MutableLiveData()
    val cities: LiveData<List<CityUi>> = _cities

    fun fetchWeather(city: String) = viewModelScope.launch {
        val weatherDomain = interactor.fetchWeather(city)
        communication.show(weatherDomain.map(mapper))
    }



    fun getCachedCities()= viewModelScope.launch {
        val res  = ArrayList<CityUi>()
        res.add(CityUi.AddCity())
       _cities.value = res
        interactor.getSavedCities()
            .collect{
            res.clear()
            res.add(CityUi.AddCity())
            res.addAll((it.map {cityDomain ->
            cityDomain.toUi()
            }))
        }
        _cities.value = res
    }


    fun removeCity(name: String) = viewModelScope.launch {
        val cityUi = CityUi.City(name)
        interactor.deleteCity(cityUi.toDomain())
    }

     fun saveCity(name: String) = viewModelScope.launch {
         val cityUi = CityUi.City(name)
         interactor.saveCity(cityUi.toDomain())
     }


    fun observeState(owner: LifecycleOwner, observer: Observer<CurrentWeatherUi>){
        communication.observe(owner, observer)
    }

}