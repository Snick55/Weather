package com.snick.weather.currentWeather.di

import com.snick.weather.currentWeather.presentation.main.WeatherStateCommunication
import dagger.Module
import dagger.Provides


@Module
class CommunicationModule {


    @Provides
    fun provideWeatherStateCommunication(): WeatherStateCommunication{
        return WeatherStateCommunication.Base()
    }

}