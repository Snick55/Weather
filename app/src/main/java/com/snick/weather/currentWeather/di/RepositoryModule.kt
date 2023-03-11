package com.snick.weather.currentWeather.di

import com.snick.weather.currentWeather.data.CurrentWeatherRepository
import com.snick.weather.currentWeather.data.PreferenceDataStore
import com.snick.weather.currentWeather.data.cache.CacheCityDataSource
import com.snick.weather.currentWeather.data.mappers.WeatherDataToDomainMapper
import com.snick.weather.currentWeather.data.cloud.CloudWeatherDataSource
import com.snick.weather.currentWeather.data.mappers.CityDataToDomainMapper
import dagger.Module
import dagger.Provides

@Module(includes = [MappersModule::class,NetworkModule::class])
class RepositoryModule {

    @Provides
    fun provideCurrentWeatherRepository(
        cloudWeatherDataSource: CloudWeatherDataSource,
        mapper: WeatherDataToDomainMapper,
        cacheCityDataSource: CacheCityDataSource,
        cityDataToDomainMapper: CityDataToDomainMapper,
        preferenceDataStore: PreferenceDataStore
    ): CurrentWeatherRepository{
        return CurrentWeatherRepository.Base(cloudWeatherDataSource,mapper,cacheCityDataSource,cityDataToDomainMapper,preferenceDataStore)
    }

}

