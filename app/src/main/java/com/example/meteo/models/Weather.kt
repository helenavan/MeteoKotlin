package com.example.meteo.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Weather(@SerializedName("id") @Expose var id:Int,
                   @SerializedName("main") @Expose var main:String,
                   @SerializedName("description") @Expose var description:String,
                   @SerializedName("icon") @Expose var icon:String)