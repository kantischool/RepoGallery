package com.findrepo.repogallery.ui.comman

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.findrepo.repogallery.ui.theme.colorBackground
import com.findrepo.repogallery.ui.theme.colorBlack
import com.findrepo.repogallery.ui.theme.colorSecondary

@Composable
fun AppScaffold(
    modifier: Modifier = Modifier,
    title: String = "",
    titleColor: Color = colorSecondary,
    backgroundColor: Color = colorBackground,
    showToolbar: Boolean = true,
    toolbarBackgroundColor: Color = colorBackground,
    @DrawableRes navigationIconResId: Int = 0,
    onNavigationIconClick: () -> Unit = {},
    showNavigationIcon: Boolean = true,
    iconColor: Color = colorBlack,
    snackBarHostState: SnackbarHostState = remember { SnackbarHostState() },
    content: @Composable (PaddingValues) -> Unit,
) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        containerColor = backgroundColor,
        topBar = {
            if (showToolbar) {
                AppTopBar(
                    modifier = Modifier
                        .background(toolbarBackgroundColor)
                        .padding(start = 12.dp, end = 12.dp),
                    title = title,
                    titleColor = titleColor,
                    backgroundColor = toolbarBackgroundColor,
                    navigationIconResId = navigationIconResId,
                    showNavigationIcon = showNavigationIcon,
                    onNavigationIconClick = onNavigationIconClick,
                    iconColor = iconColor,
                )
            }
        },
        snackbarHost = {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .safeDrawingPadding(),
                contentAlignment = Alignment.TopCenter,
            ) {
                SnackbarHost(
                    hostState = snackBarHostState,
                    snackbar = { snackBarData ->
                        AppSnackBar(
                            modifier = Modifier.padding(12.dp),
                            snackBarData = snackBarData,
                        )
                    }
                )
            }
        }
    ) { contentPadding ->
        content(contentPadding)
    }
}