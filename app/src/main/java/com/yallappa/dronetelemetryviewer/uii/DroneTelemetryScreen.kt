package com.yallappa.dronetelemetryviewer.uii

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.yallappa.dronetelemetryviewer.viewmodel.TelemetryViewModel

@Composable
fun DroneTelemetryScreen() {

    val viewModel = remember { TelemetryViewModel() }

    var ip by remember { mutableStateOf("") }
    var port by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(
            text = "🚁 Drone Telemetry Viewer",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(16.dp))

        Card(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {

                OutlinedTextField(
                    value = ip,
                    onValueChange = { ip = it },
                    label = { Text("IP Address") },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = port,
                    onValueChange = { port = it },
                    label = { Text("Port") },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(8.dp))

                if (errorMessage.isNotEmpty()) {
                    Text(
                        text = errorMessage,
                        color = MaterialTheme.colorScheme.error
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                Button(
                    onClick = {

                        if (
                            !ip.matches(
                                Regex(
                                    "^((25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)\\.){3}(25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)$"
                                )
                            )
                        ) {
                            errorMessage = "Invalid IP Address"
                            return@Button
                        }

                        if (port.toIntOrNull() == null) {
                            errorMessage = "Port must be numeric"
                            return@Button
                        }

                        val portNumber = port.toInt()

                        if (portNumber !in 1..65535) {
                            errorMessage = "Port must be between 1 and 65535"
                            return@Button
                        }

                        errorMessage = ""

                        if (viewModel.connected)
                            viewModel.disconnect()
                        else
                            viewModel.connect()
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        if (viewModel.connected)
                            "Disconnect"
                        else
                            "Connect"
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text("📶 Connection Status : ${viewModel.connectionStatus}")
        Text("📦 Packet Status : ${viewModel.packetStatus}")

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            if (viewModel.connected)
                "🟢 Connected"
            else
                "🔴 Disconnected"
        )

        Spacer(modifier = Modifier.height(12.dp))

        Card(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {

                Text(
                    text = "📡 Live Telemetry",
                    style = MaterialTheme.typography.titleLarge
                )

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    if (viewModel.connected)
                        "📍 Latitude : ${viewModel.latitude}"
                    else
                        "📍 Latitude : N/A"
                )

                Text(
                    if (viewModel.connected)
                        "📍 Longitude : ${viewModel.longitude}"
                    else
                        "📍 Longitude : N/A"
                )

                Text(
                    if (viewModel.connected)
                        "🛫 Altitude : ${viewModel.altitude} m"
                    else
                        "🛫 Altitude : N/A"
                )

                Text("🛰 Flight Mode : ${viewModel.flightMode}")
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        Card(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {

                Text(
                    text = "🔋 Battery",
                    style = MaterialTheme.typography.titleMedium
                )

                Spacer(modifier = Modifier.height(8.dp))

                LinearProgressIndicator(
                    progress = { viewModel.battery / 100f },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text("${viewModel.battery}%")

                if (viewModel.battery < 20 && viewModel.connected) {
                    Text("⚠️ Low Battery Warning")
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "🎮 Drone Controls",
            style = MaterialTheme.typography.titleLarge
        )

        Spacer(modifier = Modifier.height(12.dp))

        Row {

            Button(
                onClick = { viewModel.arm() }
            ) {
                Text("🚀 Arm")
            }

            Spacer(modifier = Modifier.width(8.dp))

            Button(
                onClick = { viewModel.disarm() }
            ) {
                Text("🛑 Disarm")
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        Row {

            Button(
                onClick = {
                    viewModel.flightMode = "TAKEOFF"
                }
            ) {
                Text("🛫 Takeoff")
            }

            Spacer(modifier = Modifier.width(8.dp))

            Button(
                onClick = {
                    viewModel.flightMode = "RTL"
                }
            ) {
                Text("🏠 RTL")
            }
        }
    }
}