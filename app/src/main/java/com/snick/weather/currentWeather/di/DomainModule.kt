package com.snick.weather.currentWeather.di

import com.snick.weather.currentWeather.data.CurrentWeatherRepository
import com.snick.weather.currentWeather.domain.LastCityUseCase
import com.snick.weather.currentWeather.domain.WeatherInteractor
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
class DomainModule {

    @Provides
    fun provideWeatherInteractor(
        repository: CurrentWeatherRepository
    ): WeatherInteractor{
        return WeatherInteractor.Base(repository)
    }

    @Provides
    fun provideLastCityUseCase(
        repository: CurrentWeatherRepository
    ): LastCityUseCase{
        return LastCityUseCase.Base(repository)
    }

}