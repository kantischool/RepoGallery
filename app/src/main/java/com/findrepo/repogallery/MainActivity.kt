package com.findrepo.repogallery

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.findrepo.navigation.AppNavigationGraph
import com.findrepo.repogallery.ui.theme.RepoGalleryTheme
import com.findrepo.utility.util.ConnectionState
import com.findrepo.utility.util.connectivityState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RepoGalleryTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    AppEntryPoint(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun AppEntryPoint(modifier: Modifier) {
    Surface(
        modifier = modifier.fillMaxSize(),
    ) {
        AppNavigationGraph()

        // This will cause re-composition on every network state change
        val connection by connectivityState()
        val isConnected = connection === ConnectionState.Connected
        if (!isConnected) {
            // no internet
        }
    }
}