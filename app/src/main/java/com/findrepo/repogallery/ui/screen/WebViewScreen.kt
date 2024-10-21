package com.findrepo.repogallery.ui.screen

import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import com.findrepo.repogallery.R
import com.findrepo.repogallery.ui.comman.AppScaffold

@Composable
fun WebViewScreen(
    url: () -> String,
    repoName: () -> String = { "" },
    onBack: () -> Unit = {},
) {
    WebViewContent(
        url = url,
        repoName = repoName,
        onBack = onBack,
    )
}

@Composable
fun WebViewContent(
    url: () -> String,
    repoName: () -> String,
    onBack: () -> Unit = {},
) {
    AppScaffold(
        title = repoName(),
        showNavigationIcon = true,
        navigationIconResId = R.drawable.ic_back,
        onNavigationIconClick = onBack
    ) { contentPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(contentPadding)
        ) {
            AndroidView(
                factory = { context ->
                    WebView(context).apply {
                        layoutParams = ViewGroup.LayoutParams(
                            ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.MATCH_PARENT,
                        )

                        settings.javaScriptEnabled = true
                        settings.domStorageEnabled = true
                        webViewClient = WebViewClient()
                    }
                },
                update = {
                    it.loadUrl(url())
                },
                modifier = Modifier.fillMaxHeight()
            )
        }
    }
}
