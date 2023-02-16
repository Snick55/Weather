package com.snick.weather.currentWeather.data

import com.snick.weather.currentWeather.data.cloud.CloudWeatherDataSource
import com.snick.weather.currentWeather.data.mappers.WeatherDataToDomainMapper
import com.snick.weather.currentWeather.domain.*
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Test
import java.lang.Exception
import java.net.UnknownHostException

class CurrentCurrentWeatherDataRepositoryTest{

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
        val expected = CurrentWeatherDomain.Success(
            WeatherDomain(
                10, 5.0, 20,
                3.1, 33, "London", 25, "cloudy", 10.0
            )
        )
        assertEquals(expected, actual)
    }

    @Test
    fun `test error with UnknownHostException `() = runBlocking{
        val cloudWeatherDataSource = CloudDataSourceTest(isSuccess = false,exception = exception)
        val mapper = DataToDomainMapper(noInternetConnectionException)

        val repository = CurrentWeatherRepository.Base(cloudWeatherDataSource,mapper)

        val actual = repository.fetchCurrentWeather("London")
        val expected = CurrentWeatherDomain.Fail(noInternetConnectionException)
        assertEquals(expected, actual)
    }


    @Test
    fun `test error with weatherApiError `() = runBlocking{
        val cloudWeatherDataSource = CloudDataSourceTest(isSuccess = false,exception = weatherApiError)
        val mapper = DataToDomainMapper(serviceUnavailableException)

        val repository = CurrentWeatherRepository.Base(cloudWeatherDataSource,mapper)

        val actual = repository.fetchCurrentWeather("London")
        val expected = CurrentWeatherDomain.Fail(serviceUnavailableException)
        assertEquals(expected, actual)
    }



    class CloudDataSourceTest(private val isSuccess: Boolean = true,private val exception: Exception):CloudWeatherDataSource {

        override suspend fun fetchCurrentWeather(city: String): CurrentWeatherData {
          return  if (isSuccess){
              CurrentWeatherData.Success(
                     WeatherData(
                         10, 5.0, 20,
                         3.1, 33, "London", 25, "cloudy", 10.0
                     )
                 )
            }else CurrentWeatherData.Fail(exception)
        }
    }


    class DataToDomainMapper(
        private val appException: ApplicationExceptions
    ) : WeatherDataToDomainMapper {
        override fun map(weatherData: WeatherData): CurrentWeatherDomain {
           return CurrentWeatherDomain.Success(weatherData.toDomain())
        }

        override fun map(exception: Exception): CurrentWeatherDomain {
            val applicationException =  when (exception){
                is UnknownHostException ->  appException
                is WeatherApiException ->  appException
                else -> ServiceUnavailableException("something went wrong")
            }
            return CurrentWeatherDomain.Fail(applicationException)
        }
    }
}