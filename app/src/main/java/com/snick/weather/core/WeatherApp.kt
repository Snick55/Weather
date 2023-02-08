package com.snick.weather.core

import android.app.Application
import com.snick.weather.currentWeather.data.CurrentWeatherRepository
import com.snick.weather.currentWeather.data.WeatherDataToDomainMapper
import com.snick.weather.currentWeather.data.cloud.CloudWeatherDataSource
import com.snick.weather.currentWeather.data.cloud.CurrentWeatherApiService
import com.snick.weather.currentWeather.di.AppComponent
import com.snick.weather.currentWeather.di.DaggerAppComponent
import com.snick.weather.currentWeather.domain.WeatherDomainToUiMapper
import com.snick.weather.currentWeather.domain.WeatherInteractor
import com.snick.weather.currentWeather.presentation.main.WeatherViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class WeatherApp: Application() {

  lateinit var weatherViewModel: WeatherViewModel
    lateinit var appComponent: AppComponent

            override fun onCreate() {
        super.onCreate()
                appComponent = DaggerAppComponent.create()
        val resourceManager = ResourceManager.Base(this)
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        val client = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org/data/2.5/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(CurrentWeatherApiService::class.java)

        val cloudWeatherDataSource = CloudWeatherDataSource.Base(service)
        val dataToDomainMapper = WeatherDataToDomainMapper.Base()
        val repository = CurrentWeatherRepository.Base(cloudWeatherDataSource,dataToDomainMapper)

        val interactor = WeatherInteractor.Base(repository)
        val domainToUiMapper = WeatherDomainToUiMapper.Base(resourceManager)
        weatherViewModel = WeatherViewModel(interactor,domainToUiMapper)
    }

}