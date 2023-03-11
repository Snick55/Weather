package com.snick.weather.currentWeather.di

import android.content.Context
import android.content.SharedPreferences
import com.snick.weather.core.ResourceManager
import dagger.Module
import dagger.Provides

@Module
class AppModule {


    @Provides
    fun provideResourceManager(context: Context): ResourceManager {
        return ResourceManager.Base(context)
    }

    @Provides
    fun provideSharedPreference(context: Context): SharedPreferences {
        return context.getSharedPreferences(APP_PREF, Context.MODE_PRIVATE)
    }

    private companion object{
        const val APP_PREF = "APP_PREF"
    }

}