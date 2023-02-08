package com.snick.weather.currentWeather.di

import com.snick.weather.currentWeather.data.WeatherDataToDomainMapper
import dagger.Module
import dagger.Provides


@Module
class MappersModule {

    @Provides
    fun provideWeatherDataToDomainMapper(): WeatherDataToDomainMapper {
        return  WeatherDataToDomainMapper.Base()
    }
}