package com.example.meteo.utils

import android.util.Log
import com.example.meteo.models.CurrentWeatherModel
import com.example.meteo.models.ForecastWeatherModel
import com.example.meteo.models.List
import com.example.meteo.models.Weather
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class CurrentStreams {

    companion object{
        fun streamFetchCurrentWeather(lat:Double?, lon:Double?,key:String,units:String):Observable<CurrentWeatherModel>{
            val currentWeatherRequest = CurrentWeatherRequest.retrofit.create(CurrentWeatherRequest::class.java)
            return currentWeatherRequest.currentWeather(lat, lon,key,units)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .timeout(10,TimeUnit.SECONDS)
        }

        fun streamFetchListForecast(lat:Double?,lon:Double?,key:String,units:String):Observable<ForecastWeatherModel>{
            val currentWeatherRequest = CurrentWeatherRequest.retrofit.create(CurrentWeatherRequest::class.java)
            return currentWeatherRequest.currentWeatherForecast(lat, lon,key,units)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .timeout(10,TimeUnit.SECONDS)
        }

        fun streamFetchForecasteAndWeather(lat:Double?, lon:Double?,key:String,units:String):Observable<CurrentWeatherModel>{
            return streamFetchListForecast(lat,lon,key,units)
                .map { it }
                .flatMap{ return@flatMap streamFetchCurrentWeather(lat,lon,key,units) }
        }
    }
}


