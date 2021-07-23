package com.nyahonk.multiplatformweather.android.datasource

import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Build
import android.os.Looper
import androidx.annotation.RequiresApi
import java.util.concurrent.Executor
import java.util.function.Consumer

class HardwareDatasource(context: Context) {

    private val locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager

    @RequiresApi(Build.VERSION_CODES.R)
    @SuppressLint("MissingPermission")
    fun getGeoData(consumer: Consumer<Location>) {

        val executor = Executor {

        }
        locationManager.getCurrentLocation(
            LocationManager.GPS_PROVIDER,
            null,
            executor,
            consumer
        )
    }

    @SuppressLint("MissingPermission")
    fun getGeoData(locationListener: LocationListener) {
        locationManager.requestSingleUpdate(LocationManager.GPS_PROVIDER, locationListener, Looper.getMainLooper())
    }
}