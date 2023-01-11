package com.studioartagonist.weather.repos

import android.location.Location
import com.studioartagonist.weather.models.CurrentWeatherModel
import com.studioartagonist.weather.models.ForecastWeatherModel
import com.studioartagonist.weather.network.NetworkService
import com.studioartagonist.weather.network.weather_api_key

class WeatherRepository {
    suspend fun fetchCurrentWeatherData(location: Location, status: Boolean = false): CurrentWeatherModel{
        val unit = if (status) "imperial" else "metric"
        val endUrl = "weather?lat=${location.latitude}&lon=${location.longitude}&units=$unit&appid=$weather_api_key"
        return NetworkService.weatherServiceApi.getCurrentWeatherData(endUrl)
    }

    suspend fun fetchForecastWeatherData(location: Location, status: Boolean =false): ForecastWeatherModel{
        val unit = if (status) "imperial" else "metric"
        val endUrl = "forecast?lat=${location.latitude}&lon=${location.longitude}&units=$unit&appid=$weather_api_key"
        return NetworkService.weatherServiceApi.getforecastWeatherData(endUrl)
    }
}