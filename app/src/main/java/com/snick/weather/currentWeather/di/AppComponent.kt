package com.snick.weather.currentWeather.di

import dagger.Component


@Component(modules = [NetworkModule::class,MappersModule::class,RepositoryModule::class])
interface AppComponent {
}