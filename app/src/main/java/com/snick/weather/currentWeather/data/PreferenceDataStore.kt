package com.snick.weather.currentWeather.data

import android.content.SharedPreferences
import android.util.Log
import javax.inject.Inject

interface PreferenceDataStore {
    fun saveLastQuery(cityName: String)
    fun readLastQuery(): String

    class Base @Inject constructor (private val sharedPreferences: SharedPreferences): PreferenceDataStore{

        override fun saveLastQuery(cityName: String) {
            sharedPreferences.edit().putString(KEY_City,cityName).apply()
        }

        override fun readLastQuery(): String {
            return sharedPreferences.getString(KEY_City,"")!!
        }


        private companion object{

            const val KEY_City = "KEY_City"
        }
    }
}