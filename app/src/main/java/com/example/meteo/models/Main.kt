package com.example.meteo.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Main(@SerializedName("temp") @Expose var temp:Double,
                @SerializedName("tempMin") @Expose var tempMin:Double,
                @SerializedName("tempMax") @Expose var tempMax:Double,
                @SerializedName("pressure") @Expose var pressure:Double,
                @SerializedName("seaLevel") @Expose var seaLevel:Double,
                @SerializedName("grndLevel") @Expose var grndLevel:Double,
                @SerializedName("humidity") @Expose var humidity:Int,
                @SerializedName("tempKf") @Expose var tempKf:Int)