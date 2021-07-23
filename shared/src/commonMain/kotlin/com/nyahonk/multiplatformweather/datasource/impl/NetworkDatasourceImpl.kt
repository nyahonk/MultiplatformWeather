package com.nyahonk.multiplatformweather.datasource.impl

import com.nyahonk.multiplatformweather.datasource.NetworkDatasource
import com.nyahonk.multiplatformweather.models.PollutionResponse
import com.nyahonk.multiplatformweather.networking.WeatherApi
import io.ktor.client.request.*

class NetworkDatasourceImpl: NetworkDatasource {

    private val weatherApi = WeatherApi()

    override suspend fun getLocalPollutionData(lon: Double, lat: Double): PollutionResponse {
        return weatherApi.httpClient.get(weatherApi.getPollutionURL(lon, lat))
    }
}