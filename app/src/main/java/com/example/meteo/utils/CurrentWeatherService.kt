package com.example.meteo.utils

import com.example.meteo.models.CurrentWeatherModel
import com.example.meteo.models.ForecastWeatherModel
import com.example.meteo.models.List
import com.example.meteo.models.Weather
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import io.reactivex.Observable

const val KEYb = "http://api.openweathermap.org/data/2.5/weather?lat=\"+latitude+\"&lon=\"+longitude+\"&appid=\"+apiKey+\"&units=metric\""
const val KEY ="6d4239c03a3040fe63c8606b269e6882"
const val URL_BASE = "http://api.openweathermap.org/data/2.5/"
const val URL_END ="&appid=6d4239c03a3040fe63c8606b269e6882&units=metric"

interface CurrentWeatherRequest {

    @GET("weather")
    fun currentWeather(@Query("lat") lat: Double?,
                       @Query ("lon") lon: Double?,
                       @Query ("APPID") appid:String,
                       @Query("units") units:String) : Observable<CurrentWeatherModel>

    @GET("forecast")
    fun currentWeatherForecast(@Query("lat") lat: Double?,
                       @Query ("lon") lon: Double?,
                       @Query ("APPID") appid:String,
                       @Query("units") units:String) : Observable<ForecastWeatherModel>

    companion object{
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(URL_BASE)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }
}