package com.yallappa.dronetelemetryviewer.data

data class TelemetryData(
    val latitude: Double,
    val longitude: Double,
    val altitude: Double,
    val battery: Int,
    val connected: Boolean,
    val armed: Boolean
)