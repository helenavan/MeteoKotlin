package com.example.meteo.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Rain(@SerializedName("_3h") @Expose var humidity:Double )
