package com.example.meteo.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ForecastWeatherModel(@SerializedName("cod") @Expose var cod:String,
                                @SerializedName("message") @Expose var message:Double,
                                @SerializedName("cnt") @Expose var cnt:Int,
                                @SerializedName("list") @Expose var list: ArrayList<List>,
                                @SerializedName("city") @Expose var city:City,
                                @SerializedName("content") @Expose var content:Int)