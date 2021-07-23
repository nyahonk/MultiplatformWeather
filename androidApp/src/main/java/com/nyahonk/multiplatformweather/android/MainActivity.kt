package com.nyahonk.multiplatformweather.android

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.nyahonk.multiplatformweather.Greeting
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar
import com.nyahonk.multiplatformweather.android.datasource.HardwareDatasource
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private val MY_PERMISSIONS_REQUEST_LOCATION = 99
    private var locationPermitted = false
    set(value) {
        field = value
        updateData()
    }

    private val mainScope = MainScope()

    private val shared = Greeting()
    lateinit var hardwareDatasource: HardwareDatasource

    lateinit var tv: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        hardwareDatasource = HardwareDatasource(this)

        tv = findViewById(R.id.text_view)
    }

    override fun onResume() {
        super.onResume()

        locationPermitted = checkLocationPermission()
    }

    private fun updateData() {
        val locationListener = LocationListener {
            mainScope.launch {
                kotlin.runCatching {
                    shared.getPollutionData(it.longitude, it.latitude)
                }.onSuccess {
                    tv.text = it
                }.onFailure {
                    Toast.makeText(
                        this@MainActivity,
                        it.localizedMessage,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
        hardwareDatasource.getGeoData(locationListener)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        when (requestCode) {
            MY_PERMISSIONS_REQUEST_LOCATION -> {

                // If request is cancelled, the result arrays are empty.
                if (grantResults.isNotEmpty()
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED
                ) {

                    // permission was granted, yay! Do the
                    // location-related task you need to do.
                    if (ContextCompat.checkSelfPermission(
                            this,
                            Manifest.permission.ACCESS_FINE_LOCATION
                        )
                        == PackageManager.PERMISSION_GRANTED
                    ) {
                        Snackbar.make(findViewById(R.id.main_view), "permissions granted", Snackbar.LENGTH_LONG).show()
                        locationPermitted = true
                    }
                } else {

                    Snackbar.make(findViewById(R.id.main_view), "permissions not granted", Snackbar.LENGTH_LONG).show()
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return
            }
        }
    }

    private fun checkLocationPermission(): Boolean {
        return if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            )
            != PackageManager.PERMISSION_GRANTED
        ) {
            //request the permission.
            requestPermissions(
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                MY_PERMISSIONS_REQUEST_LOCATION
            )
            false
        } else true
    }
}
