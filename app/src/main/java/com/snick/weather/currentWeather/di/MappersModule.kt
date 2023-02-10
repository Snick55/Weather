package com.snick.weather.currentWeather.di

import com.snick.weather.core.ResourceManager
import com.snick.weather.currentWeather.data.WeatherDataToDomainMapper
import com.snick.weather.currentWeather.domain.WeatherDomainToUiMapper
import dagger.Binds
import dagger.Module
import dagger.Provides


@Module(includes = [AppModule::class])
class MappersModule {

    @Provides
    fun bindWeatherDataToDomainMapper(): WeatherDataToDomainMapper{
        return WeatherDataToDomainMapper.Base()
    }

    @Provides
    fun bindWeatherDomainToUiMapper(
        resourceManager: ResourceManager
    ): WeatherDomainToUiMapper{
        return WeatherDomainToUiMapper.Base(resourceManager)
    }
}