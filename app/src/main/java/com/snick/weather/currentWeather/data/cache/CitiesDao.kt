package com.snick.weather.currentWeather.data.cache

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface CitiesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCity(city: CacheCity)

    @Delete
    suspend fun deleteCity(city: CacheCity)

    @Query("SELECT * FROM cities")
    fun getCities(): Flow<List<CacheCity>>

}