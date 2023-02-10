package com.snick.weather.currentWeather.di

import android.content.Context
import com.snick.weather.core.ResourceManager
import dagger.Module
import dagger.Provides

@Module
class AppModule {


    @Provides
    fun provideResourceManager(context: Context): ResourceManager {
        return ResourceManager.Base(context)
    }

}