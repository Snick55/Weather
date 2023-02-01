package com.snick.weather.currentWeather.domain

import java.lang.Exception


data class WeatherApiException(override val message: String): Exception(message)

open class ApplicationExceptions: Exception()

 class NoInternetConnectionException: ApplicationExceptions()

 class ServiceUnavailableException (override val message: String): ApplicationExceptions()
