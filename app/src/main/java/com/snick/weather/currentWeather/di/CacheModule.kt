package com.snick.weather.currentWeather.di

import android.content.Context
import androidx.room.Room
import com.snick.weather.currentWeather.data.cache.CacheCityDataSource
import com.snick.weather.currentWeather.data.cache.CitiesDao
import com.snick.weather.currentWeather.data.cache.CitiesDatabase
import com.snick.weather.currentWeather.data.cache.CitiesDatabase.Companion.DATABASE_NAME
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class CacheModule {



    @Provides
    fun provideCitiesDatabase(context: Context): CitiesDatabase{
        return Room.databaseBuilder(
            context,
            CitiesDatabase::class.java, DATABASE_NAME
        ).build()
    }

    @Provides
    fun provideDishesDao(db: CitiesDatabase): CitiesDao {
        return db.citiesDao()
    }

    @Provides
    fun provideCacheDataSourceDataSource(dao: CitiesDao): CacheCityDataSource {
        return CacheCityDataSource.Base(dao)
    }

}