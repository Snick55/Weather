package com.snick.weather.currentWeather.domain

import com.snick.weather.currentWeather.data.WeatherData
import com.snick.weather.currentWeather.data.CurrentWeatherRepository
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Test

class CurrentWeatherDataInteractorTest{

    private val noInternetConnectionException = NoInternetConnectionException()
    private val serviceUnavailableException = ServiceUnavailableException("no such city")
    private val otherServiceUnavailableException = ServiceUnavailableException("wrong api key")

    @Test
    fun `test success`() = runBlocking {
        val repository = WeatherRepositoryTest(exception = noInternetConnectionException )
        val weatherInteractor = WeatherInteractor.Base(repository)
        val actual = weatherInteractor.fetchWeather("London")
        val expected =  CurrentWeatherDomain.Success(
            WeatherDomain(
            10, 5.0, 20,
            3.1, 33, "London", 25, "cloudy", 10.0
        )
        )
        assertEquals(expected, actual)
    }

    @Test
    fun `test fail with serviceUnavailableException and message no such city`() = runBlocking {
        val repository = WeatherRepositoryTest(isSuccess = false,exception = serviceUnavailableException )
        val weatherInteractor = WeatherInteractor.Base(repository)
        val actual = weatherInteractor.fetchWeather("London")
        val expected = CurrentWeatherDomain.Fail(serviceUnavailableException)
        assertEquals(expected, actual)
    }
    @Test
    fun `test fail with serviceUnavailableException and another message`() = runBlocking {
        val repository = WeatherRepositoryTest(isSuccess = false,exception = otherServiceUnavailableException )
        val weatherInteractor = WeatherInteractor.Base(repository)
        val actual = weatherInteractor.fetchWeather("London")
        val expected = CurrentWeatherDomain.Fail(otherServiceUnavailableException)
        assertEquals(expected, actual)
    }

    @Test
    fun `test fail with noInternetConnectionException`() = runBlocking {
        val repository = WeatherRepositoryTest(isSuccess = false,exception = noInternetConnectionException )
        val weatherInteractor = WeatherInteractor.Base(repository)
        val actual = weatherInteractor.fetchWeather("London")
        val expected = CurrentWeatherDomain.Fail(noInternetConnectionException)
        assertEquals(expected, actual)
    }


    class WeatherRepositoryTest(private val isSuccess: Boolean = true, private val exception: ApplicationExceptions): CurrentWeatherRepository{
        override suspend fun fetchCurrentWeather(city: String): CurrentWeatherDomain {
         return   if (isSuccess){
                CurrentWeatherDomain.Success(
                    WeatherDomain(
                    10, 5.0, 20,
                    3.1, 33, "London", 25, "cloudy", 10.0
                )
                )
            }else{
                CurrentWeatherDomain.Fail(exception)
            }
        }
    }


}