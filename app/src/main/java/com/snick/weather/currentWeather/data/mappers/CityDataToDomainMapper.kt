package com.snick.weather.currentWeather.data.mappers

import com.snick.weather.currentWeather.data.CityData
import com.snick.weather.currentWeather.domain.CityDomain

interface CityDataToDomainMapper {

    fun map(cityData: CityData): CityDomain


    class Base: CityDataToDomainMapper{


        override fun map(cityData: CityData): CityDomain {
           return cityData.toDomainCity()
        }
    }

}