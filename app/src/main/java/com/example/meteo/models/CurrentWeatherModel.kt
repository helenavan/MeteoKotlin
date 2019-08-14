package com.example.meteo.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CurrentWeatherModel(@SerializedName("coord") @Expose var coord:Coord,
                               @SerializedName("weather") @Expose var weather: ArrayList<Weather>,
                               @SerializedName("base") @Expose var base:String,
                               @SerializedName("main") @Expose var main:Main,
                               @SerializedName("wind") @Expose var wid:Wind,
                               @SerializedName("clouds") @Expose var clouds:Clouds,
                               @SerializedName("dt") @Expose var dt:Int,
                               @SerializedName("sys") @Expose var sys:Sys,
                               @SerializedName("id") @Expose var id:Int,
                               @SerializedName("name") @Expose var name:String,
                               @SerializedName("cod") @Expose var cod:Int,
                               @SerializedName("content") @Expose var content:Int)