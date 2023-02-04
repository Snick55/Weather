package com.snick.weather.currentWeather.data.cloud

import com.snick.weather.currentWeather.data.CurrentWeatherData
import com.snick.weather.currentWeather.data.WeatherData
import com.snick.weather.currentWeather.domain.WeatherApiException
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Test
import java.lang.Exception
import java.net.UnknownHostException

class CloudCurrentWeatherDataDataSourceTest {

    private val exception = UnknownHostException()
    private val weatherApiError = WeatherApiException("no such city")

    @Test
    fun `test success with moscow city`() = runBlocking {
        val apiService = ApiServiceTest()
        val cloudWeatherDataSource = CloudWeatherDataSource.Base(apiService)

        val actual = cloudWeatherDataSource.fetchCurrentWeather("Moscow")

        val expected = CurrentWeatherData.Success(
            WeatherData(
                10, 5.0, 20,
                3.1, 33, "Moscow", 25, "cloudy", 10.0
            )
        )

        assertEquals(expected, actual)
    }

    @Test
    fun `test success with london(start with lower case) city`() = runBlocking {
        val apiService = ApiServiceTest()
        val cloudWeatherDataSource = CloudWeatherDataSource.Base(apiService)

        val actual = cloudWeatherDataSource.fetchCurrentWeather("london")

        val expected = CurrentWeatherData.Success(
            WeatherData(
                10, 5.0, 20,
                3.1, 33, "London", 25, "cloudy", 10.0
            )
        )

        assertEquals(expected, actual)
    }

    @Test
    fun `test success with mOscOw city`() = runBlocking {
        val apiService = ApiServiceTest()
        val cloudWeatherDataSource = CloudWeatherDataSource.Base(apiService)

        val actual = cloudWeatherDataSource.fetchCurrentWeather("mOscOw")

        val expected = CurrentWeatherData.Success(
            WeatherData(
                10, 5.0, 20,
                3.1, 33, "Moscow", 25, "cloudy", 10.0
            )
        )

        assertEquals(expected, actual)
    }

    @Test
    fun `test success with Москва city`() = runBlocking {
        val apiService = ApiServiceTest()
        val cloudWeatherDataSource = CloudWeatherDataSource.Base(apiService)

        val actual = cloudWeatherDataSource.fetchCurrentWeather("Москва")

        val expected = CurrentWeatherData.Success(
            WeatherData(
                10, 5.0, 20,
                3.1, 33, "Москва", 25, "cloudy", 10.0
            )
        )

        assertEquals(expected, actual)
    }


    @Test
    fun `test method without internet`() = runBlocking {
        val apiService = ApiServiceTest(isInternetOn = false, error = exception)
        val cloudWeatherDataSource = CloudWeatherDataSource.Base(apiService)

        val actual = cloudWeatherDataSource.fetchCurrentWeather("Moscow")

        val expected = CurrentWeatherData.Fail(exception)

        assertEquals(expected, actual)
    }


    @Test
    fun `test method with api error`() = runBlocking {
        val apiService = ApiServiceTest(isSuccess = false, error = weatherApiError)
        val cloudWeatherDataSource = CloudWeatherDataSource.Base(apiService)

        val actual = cloudWeatherDataSource.fetchCurrentWeather("Moscow")

        val expected = CurrentWeatherData.Fail(weatherApiError)

        assertEquals(expected, actual)
    }



    class ApiServiceTest(val isSuccess: Boolean = true, val isInternetOn: Boolean = true,val error: Exception = UnknownHostException()) :
        CurrentWeatherApiService {

        private fun validateCity(city: String): String{
            return  city.lowercase().replaceFirstChar {
                it.uppercase()
            }
        }

        override suspend fun fetchCurrentWeather(
            city: String,
            key: String,
            language: String,
            units: String
        ): WeatherCloud {
            if (!isInternetOn) throw error
            return if (isSuccess) {
                WeatherCloud(
                    Clouds((10)),
                    200,
                    main = Main(5.0, 20, 33, 3.1),
                    name = validateCity(city),
                    visibility = 25,
                    weatherDescription = listOf(WeatherDescription("cloudy")),
                    wind = Wind(10.0),
                    errorMessage = ""
                )
            } else
                WeatherCloud(requestCode = 400,errorMessage = "no such city")

        }

    }

}