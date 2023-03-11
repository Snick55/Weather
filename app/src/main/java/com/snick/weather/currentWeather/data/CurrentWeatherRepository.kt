package com.snick.weather.currentWeather.data

import com.snick.weather.currentWeather.data.cache.CacheCityDataSource
import com.snick.weather.currentWeather.data.cloud.CloudWeatherDataSource
import com.snick.weather.currentWeather.data.mappers.CityDataToDomainMapper
import com.snick.weather.currentWeather.data.mappers.WeatherDataToDomainMapper
import com.snick.weather.currentWeather.domain.CityDomain
import com.snick.weather.currentWeather.domain.CurrentWeatherDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

interface CurrentWeatherRepository {

    suspend fun fetchCurrentWeather(city: String): CurrentWeatherDomain

     fun getSavedCities(): Flow<List<CityDomain>>

     suspend fun saveCity(cityData: CityData)

     suspend fun deleteCity(cityData: CityData)

     fun saveLastCity(cityName:String)

     fun getLastCity(): String

    class Base @Inject constructor(
        private val cloudDataSource: CloudWeatherDataSource,
        private val weatherMapper: WeatherDataToDomainMapper,
        private val cacheDataSource: CacheCityDataSource,
        private val cityMapper: CityDataToDomainMapper,
        private val preferenceDataStore: PreferenceDataStore
    ) : CurrentWeatherRepository {

        override suspend fun fetchCurrentWeather(city: String): CurrentWeatherDomain {
            val weatherData = cloudDataSource.fetchCurrentWeather(city)
            return weatherData.map(weatherMapper)
        }


        override fun getSavedCities(): Flow<List<CityDomain>> {
          return  cacheDataSource.getSavedCities().map {
                it.map { cityData ->
                    cityMapper.map(cityData)
                }
            }
        }


        override suspend fun saveCity(cityData: CityData) {
            cacheDataSource.insertCity(cityData)
        }

        override suspend fun deleteCity(cityData: CityData) {
            cacheDataSource.deleteCity(cityData)
        }

        override fun saveLastCity(cityName: String) {
            preferenceDataStore.saveLastQuery(cityName)
        }

        override fun getLastCity(): String {
            return preferenceDataStore.readLastQuery()
        }
    }




}