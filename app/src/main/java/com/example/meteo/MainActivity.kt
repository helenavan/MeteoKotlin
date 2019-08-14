package com.example.meteo

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Typeface
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.meteo.models.City
import com.example.meteo.models.CurrentWeatherModel
import com.example.meteo.models.ForecastWeatherModel
import com.example.meteo.utils.CurrentStreams
import com.example.meteo.views.ForecastRecyclerView
import com.example.meteo.views.URL_ICON
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableObserver
import kotlinx.android.synthetic.main.activity_main.*
import java.math.RoundingMode
import java.text.DecimalFormat

const val TAG = "MainActivity"
const val PERMISSION_REQUEST_ACCESS_FINE_LOCATION = 100
const val MY_PERMISSIONS_REQUEST = 1

class MainActivity : AppCompatActivity() {
    private var disposable: Disposable? = null
    private val apiKey: String = "6d4239c03a3040fe63c8606b269e6882"
    private var locationManager: LocationManager? = null
    private var locationListener: LocationListener? = null
    private var latitude: Double? = null
    private var longitude: Double? = null
    // private var currentList:ArrayList<Weather>? = null
    private var recyclerView: RecyclerView? = null
    private var mAdapter: ForecastRecyclerView? = null
    private var mLayoutManager: RecyclerView.LayoutManager? = null

    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("MissingPermission")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        time_current.text = currentDate()
        locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager?

        locationListener = object : LocationListener {
            override fun onLocationChanged(location: Location?) {
                latitude = location!!.latitude
                longitude = location!!.longitude
                Log.e(TAG, "latitude : $latitude")
                executeRetrofit()
            }

            override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {

            }

            override fun onProviderEnabled(provider: String?) {

            }

            override fun onProviderDisabled(provider: String?) {

            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        this.disposeWhenDestroy()
    }

    private fun executeRetrofit() {
        this.disposable = CurrentStreams.streamFetchForecasteAndWeather(latitude, longitude, apiKey, "metric")
            .subscribeWith(object : DisposableObserver<CurrentWeatherModel>() {
                override fun onNext(t: CurrentWeatherModel) {
                    city.text = t.name
                    //remove number after .
                    val liste = arrayListOf<CurrentWeatherModel>()
                    val num = t.main.temp
                    val decimal = DecimalFormat("#.#")
                    decimal.roundingMode = RoundingMode.CEILING
                    temperature.text = decimal.format(num).toString() + " °"
                    Glide.with(applicationContext)
                        .load(URL_ICON + t.weather[0].icon + ".png")
                        .into(icon_weather)
                   // displayRecyclerView(arrayListOf(ForecastWeatherModel(city = City(name=t.name))))
                }

                override fun onComplete() {

                }

                override fun onError(e: Throwable) {
                    Log.e(TAG, "error : $e" + "$disposable")
                }

            })
        Log.e(TAG, "recyclerView : $latitude")
    }

    private fun displayRecyclerView(currentList: ArrayList<ForecastWeatherModel>) {
        currentList.listIterator()
        recycler_view.layoutManager = LinearLayoutManager(applicationContext)
        recycler_view.adapter = ForecastRecyclerView(currentList)
        recycler_view.adapter!!.notifyDataSetChanged()
        Log.e(TAG, "recyclerView list : $currentList")

    }

    private fun disposeWhenDestroy() {
        if (this.disposable != null && !this.disposable!!.isDisposed()) this.disposable!!.dispose()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
            != PackageManager.PERMISSION_GRANTED
            && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
            != PackageManager.PERMISSION_GRANTED
        ) {

        }
        locationManager!!.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 50f, locationListener)
        locationManager!!.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 50f, locationListener)

    }

    override fun onStart() {
        super.onStart()
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
            != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            )
            != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf<String>(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION),
                MY_PERMISSIONS_REQUEST
            )
            return
        }
        locationManager!!.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 50f, locationListener)
        locationManager!!.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 50f, locationListener)
    }

    override fun onStop() {
        super.onStop()
        locationManager!!.removeUpdates(locationListener)
    }

}
