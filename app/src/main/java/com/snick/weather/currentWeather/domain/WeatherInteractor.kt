package com.snick.weather.currentWeather.domain

import com.snick.weather.currentWeather.data.CityData
import com.snick.weather.currentWeather.data.CurrentWeatherRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface WeatherInteractor {

    suspend fun fetchWeather(city: String): CurrentWeatherDomain

    fun getSavedCities(): Flow<List<CityDomain>>

    suspend fun saveCity(cityDomain: CityDomain)

    suspend fun deleteCity(cityDomain: CityDomain)


    class Base @Inject constructor (
        private val repository: CurrentWeatherRepository
    ) : WeatherInteractor {

        override suspend fun fetchWeather(city: String): CurrentWeatherDomain {
            return repository.fetchCurrentWeather(city)
        }

        override fun getSavedCities(): Flow<List<CityDomain>> {
            return repository.getSavedCities()
        }

        override suspend fun saveCity(cityDomain: CityDomain) {
            repository.saveCity(cityDomain.toData())
        }

        override suspend fun deleteCity(cityDomain: CityDomain) {
            repository.deleteCity(cityDomain.toData())
        }
    }
}