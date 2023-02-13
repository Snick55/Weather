package com.snick.weather.currentWeather.di

import com.snick.weather.currentWeather.domain.WeatherDomainToUiMapper
import com.snick.weather.currentWeather.domain.WeatherInteractor
import com.snick.weather.currentWeather.presentation.main.WeatherStateCommunication
import com.snick.weather.currentWeather.presentation.main.WeatherViewModelFactory
import dagger.Module
import dagger.Provides


@Module
class ViewModelModule {

    @Provides
    fun provideWeatherViewModelFactory(
        interactor: WeatherInteractor,
        mapper: WeatherDomainToUiMapper,
        communication: WeatherStateCommunication
    ): WeatherViewModelFactory{
        return WeatherViewModelFactory(interactor,mapper,communication)
    }
}