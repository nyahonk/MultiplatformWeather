package com.nyahonk.multiplatformweather.android.datasource

import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import android.location.LocationManager
import android.os.Build
import java.util.function.Consumer

class HardwareDatasource(private val context: Context) {

    private val locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager

    @SuppressLint("MissingPermission")
    fun getGeoData(consumer: Consumer<Location>) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            locationManager.getCurrentLocation(
                LocationManager.NETWORK_PROVIDER,
                null,
                context.mainExecutor,
                consumer
            )
        } else {
            locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)?.let {
                consumer.accept(it)
            }
        }
    }

}