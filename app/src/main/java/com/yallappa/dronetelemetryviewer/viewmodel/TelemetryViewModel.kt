package com.yallappa.dronetelemetryviewer.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*

class TelemetryViewModel : ViewModel() {

    var latitude by mutableStateOf(12.9716)
    var longitude by mutableStateOf(77.5946)
    var altitude by mutableStateOf(120.0)

    var battery by mutableStateOf(100)

    var connected by mutableStateOf(false)
    var armed by mutableStateOf(false)

    var flightMode by mutableStateOf("GUIDED")

    var connectionStatus by mutableStateOf("Disconnected")

    var packetStatus by mutableStateOf("Valid Packet")

    private var job: Job? = null

    fun connect() {

        connectionStatus = "Connecting..."

        CoroutineScope(Dispatchers.Main).launch {

            delay(3000)

            connected = true
            connectionStatus = "Connected"

            job = launch {

                while (connected) {

                    delay(1000)

                    latitude += 0.0001
                    longitude += 0.0001
                    altitude += 1

                    if (battery > 0) {
                        battery--
                    }

                    if (battery <= 5) {

                        connected = false
                        connectionStatus = "Stream Interrupted"

                        cancel()
                    }
                }
            }
        }
    }

    fun disconnect() {
        connected = false
        connectionStatus = "Disconnected"
        job?.cancel()
    }

    fun arm() {
        armed = true
        flightMode = "ARMED"
    }

    fun disarm() {
        armed = false
        flightMode = "STANDBY"
    }
}