package com.example.meteo.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class City (@SerializedName("id") @Expose var id:Int,
                 @SerializedName("name") @Expose var name:String,
                 @SerializedName("coord") @Expose var coord:Coord,
                 @SerializedName("country") @Expose var country:String) {
}
