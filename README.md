# 🚁 Drone Telemetry Viewer

## Overview

Drone Telemetry Viewer is an Android application developed using Kotlin and Jetpack Compose. The application simulates a drone telemetry dashboard that allows users to configure connection settings, monitor telemetry data, and perform basic drone control operations.

---

## Features

* IP Address and Port Configuration
* Connect / Disconnect Functionality
* Telemetry Monitoring
* Battery Status Monitoring
* Flight Mode Display
* Arm / Disarm Controls
* Takeoff and Return-To-Launch (RTL) Controls
* Input Validation
* Error Handling
* Modern Jetpack Compose User Interface

---

## Setup Steps

### Prerequisites

* Android Studio
* JDK 11 or above
* Android SDK 26+

### Clone the Repository

```bash
git clone https://github.com/ysanadi-dev/droneApk.git
```

### Open Project

1. Open Android Studio
2. Select **Open Project**
3. Choose the cloned repository folder

### Build and Run

1. Sync Gradle dependencies
2. Connect an Android device or start an emulator
3. Click **Run ▶**
4. Enter IP Address and Port
5. Connect and monitor telemetry data

---

## Libraries Used

### Jetpack Compose

Used for building the entire user interface.

### Material 3

Provides Material Design UI components.

### AndroidX Activity Compose

Integrates Compose with Android Activities.

### AndroidX Lifecycle

Supports lifecycle-aware components.

### Kotlin Coroutines

Used to simulate live telemetry updates asynchronously.

---

## Architecture Explanation

The project follows a simple MVVM (Model-View-ViewModel) architecture.

### Model Layer

**TelemetryData.kt**

Responsible for storing telemetry-related information:

* Latitude
* Longitude
* Altitude
* Battery Percentage
* Flight Mode

### View Layer

**DroneTelemetryScreen.kt**

Responsible for:

* Displaying UI
* User Input Handling
* Telemetry Visualization
* Drone Control Actions

Built entirely using Jetpack Compose.

### ViewModel Layer

**TelemetryViewModel.kt**

Responsible for:

* Managing UI State
* Simulating Telemetry Data
* Connection Management
* Drone Command Handling
* Battery Updates

---

## Validation and Error Handling

The application validates:

* Invalid IP Address
* Invalid Port Number
* Empty Inputs

The application handles:

* Connection Status Changes
* Low Battery Warning
* Invalid User Input
* Simulated Packet Status Updates

---

## Assumptions

* Drone telemetry is simulated and not connected to a real drone.
* Connection requests are simulated locally.
* Flight control commands are demonstration actions.
* Telemetry values are generated within the application for testing purposes.

---

## Limitations

* No real MAVLink integration.
* No actual drone communication.
* No network socket implementation.
* Telemetry data is simulated.
* No user authentication.
* Limited offline data storage.

---

## Future Enhancements

* MAVLink Integration
* Real-Time Telemetry Streaming
* Google Maps Integration
* Mission Planning Support
* Telemetry History Storage
* Real Drone Connectivity

---

## Author

**Yallappa Sanadi**

GitHub: https://github.com/ysanadi-dev
