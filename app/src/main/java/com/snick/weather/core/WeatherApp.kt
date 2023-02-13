package com.snick.weather.core

import android.app.Application
import android.content.Context
import com.snick.weather.currentWeather.di.AppComponent
import com.snick.weather.currentWeather.di.DaggerAppComponent


class WeatherApp: Application() {

    lateinit var appComponent: AppComponent

            override fun onCreate() {
        super.onCreate()
                appComponent = DaggerAppComponent.builder()
                    .context(context = this)
                    .build()
    }


}

val Context.appComponent: AppComponent
    get() = when(this){
        is WeatherApp -> appComponent
        else-> this.applicationContext.appComponent
    }
