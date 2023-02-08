package com.snick.weather.currentWeather.di

import com.snick.weather.currentWeather.data.CurrentWeatherRepository
import com.snick.weather.currentWeather.data.WeatherDataToDomainMapper
import com.snick.weather.currentWeather.data.cloud.CloudWeatherDataSource
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {

    @Provides
    fun provideCurrentWeatherRepository(
        cloudWeatherDataSource: CloudWeatherDataSource,
        mapper: WeatherDataToDomainMapper
    ): CurrentWeatherRepository {
        return CurrentWeatherRepository.Base(cloudWeatherDataSource, mapper)
    }

}