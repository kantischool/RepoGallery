package com.findrepo.utility.util

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.produceState
import androidx.compose.ui.platform.LocalContext
import com.findrepo.utility.extention.currentConnectivityState
import com.findrepo.utility.extention.observeConnectivityAsFlow

@Composable
fun connectivityState(): State<ConnectionState> {
    val context = LocalContext.current

    // Creates a State<ConnectionState> with current connectivity state as initial value
    return produceState(initialValue = context.currentConnectivityState) {
        // In a coroutine, can make suspend calls
        context.observeConnectivityAsFlow().collect { value = it }
    }
}

sealed class ConnectionState {
    data object Connected : ConnectionState()
    data object Disconnected : ConnectionState()
}