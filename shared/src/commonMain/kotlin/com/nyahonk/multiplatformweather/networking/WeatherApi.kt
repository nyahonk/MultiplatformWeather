package com.nyahonk.multiplatformweather.networking

import io.ktor.client.*
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.*
import kotlinx.serialization.json.Json

class WeatherApi {

    val httpClient = HttpClient {
        install(JsonFeature) {
            val json = Json { ignoreUnknownKeys = true }
            serializer = KotlinxSerializer(json)
        }
    }

    fun getPollutionURL(lon: Double, lat: Double): String {
        return "$API_URL$API_VER$API_POLLUTION?lat=$lat&lon=$lon&appid=$PRIVATE_API_KEY"
    }

    companion object {
        private const val API_URL = "https://api.openweathermap.org/data/"
        private const val API_VER = "2.5/"
        private const val API_POLLUTION = "/air_pollution"
        private const val PRIVATE_API_KEY = ""
    }
}