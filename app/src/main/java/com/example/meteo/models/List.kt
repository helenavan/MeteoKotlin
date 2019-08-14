package com.example.meteo.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlin.collections.List

data class List(@SerializedName("dt") @Expose var dt:Int,
                @SerializedName("main") @Expose var main:Main,
                @SerializedName("clouds") @Expose var clouds:Clouds,
                @SerializedName("wind") @Expose var wind:Wind,
                @SerializedName("sys") @Expose var sys:Sys,
                @SerializedName("dtTxt") @Expose var dtTxt:String,
                @SerializedName("weather") @Expose var weather: ArrayList<Weather>,
                @SerializedName("rain") @Expose var rain:Rain)