package com.example.meteo.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Coord(@SerializedName("latitude") @Expose var latitude:Double,
                 @SerializedName("longitude") @Expose var longitude:Double)
