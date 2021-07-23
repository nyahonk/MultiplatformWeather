package com.nyahonk.multiplatformweather

import com.nyahonk.multiplatformweather.datasource.NetworkDatasource
import com.nyahonk.multiplatformweather.datasource.impl.NetworkDatasourceImpl

class Greeting {

    private val networkDatasource: NetworkDatasource = NetworkDatasourceImpl()

    suspend fun getPollutionData(lon: Double, lat: Double): String {
        return networkDatasource.getLocalPollutionData(lon, lat).toString()
    }
}