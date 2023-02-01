package com.snick.weather.currentWeather.domain

import java.lang.Exception


class WeatherApiException(message: String): Exception(message)

open class ApplicationExceptions(message: String = ""): Exception(message)

class NoInternetConnectionException: ApplicationExceptions()

class ServiceUnavailableException (message: String): ApplicationExceptions(message)
