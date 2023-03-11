package com.snick.weather.currentWeather.di

import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.snick.weather.currentWeather.data.PreferenceDataStore
import com.snick.weather.currentWeather.data.cache.CacheCityDataSource
import com.snick.weather.currentWeather.data.cache.CitiesDao
import com.snick.weather.currentWeather.data.cache.CitiesDatabase
import com.snick.weather.currentWeather.data.cache.CitiesDatabase.Companion.DATABASE_NAME
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [AppModule::class])
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

    @Provides
    fun providePreferenceDataStore(sharedPreferences: SharedPreferences): PreferenceDataStore{
        return PreferenceDataStore.Base(sharedPreferences)
    }

}