package com.snick.weather.currentWeather.data.cache

import androidx.room.Database
import androidx.room.RoomDatabase
import com.snick.weather.currentWeather.data.cache.CitiesDatabase.Companion.DATABASE_VERSION

@Database(entities = [CacheCity::class], version = DATABASE_VERSION )
abstract class CitiesDatabase: RoomDatabase() {

    abstract fun citiesDao(): CitiesDao


    companion object{
        const val DATABASE_NAME = "Cities"
        const val DATABASE_VERSION = 1
    }
}