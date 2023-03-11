package com.snick.weather.currentWeather.di

import android.content.Context
import com.snick.weather.MainActivity
import com.snick.weather.currentWeather.presentation.main.WeatherFragment
import dagger.BindsInstance
import dagger.Component


@Component(modules = [NetworkModule::class, MappersModule::class, RepositoryModule::class,
    DomainModule::class, ViewModelModule::class, AppModule::class,CommunicationModule::class,CacheModule::class])
interface AppComponent {


    fun inject(weatherFragment: WeatherFragment)
    fun inject(mainActivity: MainActivity)


    @Component.Builder
    interface Builder {

        @BindsInstance
        fun context(context: Context): Builder

        fun build(): AppComponent
    }

}