package com.nyahonk.multiplatformweather.datasource

import com.nyahonk.multiplatformweather.models.PollutionResponse

interface NetworkDatasource {

    suspend fun getLocalPollutionData(lon: Double, lat: Double): PollutionResponse
}