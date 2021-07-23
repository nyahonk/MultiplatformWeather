package com.nyahonk.multiplatformweather.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PollutionResponse (
    @SerialName("coord")
    val coord: Coord,
    @SerialName("list")
    val list: List<ListElement>
)

@Serializable
data class Coord (
    @SerialName("lon")
    val lon: Double,
    @SerialName("lat")
    val lat: Double
)

@Serializable
data class ListElement (
    @SerialName("main")
    val main: Main,
    @SerialName("components")
    val components: Map<String, Double>,
    @SerialName("dt")
    val dt: Long
)

@Serializable
data class Main (
    @SerialName("aqi")
    val aqi: Long
)
