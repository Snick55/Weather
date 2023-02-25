package com.snick.weather.currentWeather.data.cache

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface CitiesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCity(city: CacheCity)

    @Query("DELETE FROM cities WHERE city_name = :name")
    suspend fun deleteCity(name: String)

    @Query("SELECT * FROM cities")
    fun getCities(): Flow<List<CacheCity>>

}