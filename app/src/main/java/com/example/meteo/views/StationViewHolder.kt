package com.example.meteo.views

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.meteo.R
import com.example.meteo.getDate
import com.example.meteo.models.CurrentWeatherModel
import com.example.meteo.models.ForecastWeatherModel
import com.example.meteo.models.List
import java.text.SimpleDateFormat
import java.util.*

const val URL_ICON="http://openweathermap.org/img/w/"

class StationViewHolder(inflater: LayoutInflater, parent: ViewGroup)
    :RecyclerView.ViewHolder(inflater.inflate(R.layout.list_weather,parent, false)) {

    private lateinit var cityF:TextView
    private lateinit var temperature:TextView
    private lateinit var time:TextView
    private lateinit var iconF:ImageView

    init {
        cityF = itemView.findViewById(R.id.description_forecast)
        temperature = itemView.findViewById(R.id.temp_forecast)
        time = itemView.findViewById(R.id.dt)
        iconF = itemView.findViewById(R.id.icon_f)
    }

    fun updateWeather(
        listW: List
    ){
        cityF.text = listW.weather[0].description
        temperature.text = listW.main.temp.toString()
        time.text = getDate(listW.dt.toLong())
        Log.e("Holder", "city : $cityF")
        Glide.with(itemView)
            .load(URL_ICON+listW.weather[0].icon+".png")
            .into(iconF)
    }

}