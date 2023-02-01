package com.snick.weather.currentWeather.data

import com.snick.weather.core.Weather
import com.snick.weather.currentWeather.data.cloud.CloudWeatherDataSource
import com.snick.weather.currentWeather.data.cloud.CloudWeatherDataSourceTest
import com.snick.weather.currentWeather.domain.*
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Test
import java.lang.Exception
import java.net.UnknownHostException

class CurrentWeatherRepositoryTest{

    private val exception = UnknownHostException()
    private val weatherApiError = WeatherApiException("no such city")
    private val noInternetConnectionException = NoInternetConnectionException()
    private val serviceUnavailableException = ServiceUnavailableException("no such city")


    @Test
    fun test_success() = runBlocking{
        val cloudWeatherDataSource = CloudDataSourceTest(exception = exception)
        val mapper = DataToDomainMapper(noInternetConnectionException)

        val repository = CurrentWeatherRepository.Base(cloudWeatherDataSource,mapper)

        val actual = repository.fetchCurrentWeather("London")
        val expected = WeatherDomain.Success( Weather(
            10, 5.0, 20,
            3.1, 33, "London", 25, "cloudy", 10.0
        ))
        assertEquals(expected, actual)
    }

    @Test
    fun `test error with UnknownHostException `() = runBlocking{
        val cloudWeatherDataSource = CloudDataSourceTest(isSuccess = false,exception = exception)
        val mapper = DataToDomainMapper(noInternetConnectionException)

        val repository = CurrentWeatherRepository.Base(cloudWeatherDataSource,mapper)

        val actual = repository.fetchCurrentWeather("London")
        val expected = WeatherDomain.Fail(noInternetConnectionException)
        assertEquals(expected, actual)
    }


    @Test
    fun `test error with weatherApiError `() = runBlocking{
        val cloudWeatherDataSource = CloudDataSourceTest(isSuccess = false,exception = weatherApiError)
        val mapper = DataToDomainMapper(serviceUnavailableException)

        val repository = CurrentWeatherRepository.Base(cloudWeatherDataSource,mapper)

        val actual = repository.fetchCurrentWeather("London")
        val expected = WeatherDomain.Fail(serviceUnavailableException)
        assertEquals(expected, actual)
    }



    class CloudDataSourceTest(private val isSuccess: Boolean = true,private val exception: Exception):CloudWeatherDataSource {

        override suspend fun fetchCurrentWeather(city: String): WeatherData {
          return  if (isSuccess){
                 WeatherData.Success( Weather(
                    10, 5.0, 20,
                    3.1, 33, "London", 25, "cloudy", 10.0
                ))
            }else WeatherData.Fail(exception)
        }
    }


    class DataToDomainMapper(
        private val appException: ApplicationExceptions
    ) : WeatherDataToDomainMapper{
        override fun map(weather: Weather): WeatherDomain {
           return WeatherDomain.Success(weather)
        }

        override fun map(exception: Exception): WeatherDomain {
            val applicationException =  when (exception){
                is UnknownHostException ->  appException
                is WeatherApiException ->  appException
                else -> ServiceUnavailableException("something went wrong")
            }
            return WeatherDomain.Fail(applicationException)
        }
    }
}