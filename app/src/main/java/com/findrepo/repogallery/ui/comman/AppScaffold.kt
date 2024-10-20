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
import com.findrepo.repogallery.ui.theme.colorPrimary
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
    showNotificationIcon: Boolean = true,
    notificationCount: Int = 0,
    onNotificationClick: () -> Unit = {},
    showOtherAction: Boolean = false,
    showNavigationIcon: Boolean = true,
    @DrawableRes otherActionIconResId: Int = 0,
    onOtherActionClick: () -> Unit = {},
    iconColor: Color = colorPrimary,
    snackbarHostState: SnackbarHostState = remember { SnackbarHostState() },
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
                    notificationCount = notificationCount,
                    navigationIconResId = navigationIconResId,
                    showNavigationIcon = showNavigationIcon,
                    onNavigationIconClick = onNavigationIconClick,
                    onNotificationClick = onNotificationClick,
                    showNotificationIcon = showNotificationIcon,
                    showOtherAction = showOtherAction,
                    otherActionIconResId = otherActionIconResId,
                    onOtherActionClick = onOtherActionClick,
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
                    hostState = snackbarHostState,
                    snackbar = { snackbarData ->
                        AppSnackBar(
                            modifier = Modifier.padding(12.dp),
                            snackbarData = snackbarData,
                        )
                    }
                )
            }
        }
    ) { contentPadding ->
        content(contentPadding)
    }
}