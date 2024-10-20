package com.findrepo.utility.extention

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import com.findrepo.utility.util.ConnectionState
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow

/**
 * network utility to get current state of internet connection
 */
val Context.currentConnectivityState: ConnectionState
    get() {
        val connectivityManager =
            getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return getCurrentConnectivityState(connectivityManager)
    }

private fun getCurrentConnectivityState(
    connectivityManager: ConnectivityManager
): ConnectionState {
    val connected = connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
        ?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
        ?: false

    return if (connected) ConnectionState.Connected else ConnectionState.Disconnected
}

/**
 * network utility to observe availability or unavailability of internet connection
 */
fun Context.observeConnectivityAsFlow() = callbackFlow {
    val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    val networkCallback = networkCallback { connectionState -> trySend(connectionState) }
    connectivityManager.registerDefaultNetworkCallback(networkCallback)

    // Set current state
    val currentState = getCurrentConnectivityState(connectivityManager)
    trySend(currentState)

    // Remove callback when not used
    awaitClose {
        // Remove listeners
        connectivityManager.unregisterNetworkCallback(networkCallback)
    }
}

fun networkCallback(callback: (ConnectionState) -> Unit) = object : ConnectivityManager.NetworkCallback() {
    override fun onAvailable(network: Network) {
        super.onAvailable(network)
        callback(ConnectionState.Connected)
    }

    override fun onLosing(network: Network, maxMsToLive: Int) {
        super.onLosing(network, maxMsToLive)
        callback(ConnectionState.Disconnected)
    }

    override fun onLost(network: Network) {
        super.onLost(network)
        callback(ConnectionState.Disconnected)
    }

    override fun onUnavailable() {
        super.onUnavailable()
        callback(ConnectionState.Disconnected)
    }
}