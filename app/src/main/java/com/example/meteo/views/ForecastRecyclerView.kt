package com.example.meteo.views

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.meteo.models.CurrentWeatherModel
import com.example.meteo.models.ForecastWeatherModel
import com.example.meteo.models.List

class ForecastRecyclerView(private val listForecasts: ArrayList<ForecastWeatherModel>):RecyclerView.Adapter<StationViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StationViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return StationViewHolder(inflater,parent)
    }

    override fun getItemCount(): Int = listForecasts.size

    override fun onBindViewHolder(holder: StationViewHolder, position: Int) {
        val current: ForecastWeatherModel = listForecasts[position]
        holder.updateWeather(current)
        Log.e("Recycler", " adress : $current")
    }
}