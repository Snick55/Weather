package com.snick.weather.currentWeather.di

import com.snick.weather.currentWeather.domain.LastCityUseCase
import com.snick.weather.currentWeather.domain.WeatherDomainToUiMapper
import com.snick.weather.currentWeather.domain.WeatherInteractor
import com.snick.weather.currentWeather.presentation.main.MainViewModelFactory
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
        communication: WeatherStateCommunication,
        useCase: LastCityUseCase
    ): WeatherViewModelFactory{
        return WeatherViewModelFactory(interactor,mapper,communication,useCase)
    }


    @Provides
    fun provideMainViewModelFactory(
        useCase: LastCityUseCase
    ): MainViewModelFactory{
        return MainViewModelFactory(useCase)
    }
}