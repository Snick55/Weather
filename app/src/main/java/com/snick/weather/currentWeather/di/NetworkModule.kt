package com.snick.weather.currentWeather.di

import com.snick.weather.currentWeather.data.cloud.CloudWeatherDataSource
import com.snick.weather.currentWeather.data.cloud.CurrentWeatherApiService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class NetworkModule {

    @Provides
    fun provideCloudWeatherDataSource(apiService: CurrentWeatherApiService): CloudWeatherDataSource{
        return CloudWeatherDataSource.Base(apiService)
    }

    @Provides
    fun provideCurrentWeatherApiService(
        retrofit: Retrofit
    ): CurrentWeatherApiService {
        return retrofit.create(CurrentWeatherApiService::class.java)
    }

    @Provides
    fun provideInterceptor(): HttpLoggingInterceptor{
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        return  interceptor
    }

    @Provides
    fun provideOkHttpClient(interceptor: HttpLoggingInterceptor, ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()
    }

    @Provides
    fun provideRetrofit(
        client: OkHttpClient
    ): Retrofit{
        return Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org/data/2.5/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}