package com.yallappa.dronetelemetryviewer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.yallappa.dronetelemetryviewer.uii.DroneTelemetryScreen

class MainActivity : ComponentActivity() {




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            DroneTelemetryScreen()
        }
    }
}