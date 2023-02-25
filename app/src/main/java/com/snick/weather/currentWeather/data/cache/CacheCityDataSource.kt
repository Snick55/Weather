package com.snick.weather.currentWeather.data.cache

import com.snick.weather.currentWeather.data.CityData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

interface CacheCityDataSource {

    fun getSavedCities(): Flow<List<CityData>>

    suspend fun insertCity(city: CityData)

    suspend fun  deleteCity(city: CityData)


    class Base @Inject constructor (
        private val dao: CitiesDao
    ): CacheCityDataSource{

        override  fun getSavedCities(): Flow<List<CityData>>  {
            return dao.getCities().map {
                it.map { cacheCity->
                    CityData(cacheCity.name)
                }
            }
        }

        override suspend fun insertCity(city: CityData) {
            dao.insertCity(city.map())
        }

        override suspend fun deleteCity(city: CityData) {
            dao.deleteCity(city.map().name)
        }
    }

}