package com.findrepo.repogallery.ui.comman

import androidx.annotation.DrawableRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Badge
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.findrepo.repogallery.R
import com.findrepo.repogallery.ui.theme.colorBackground
import com.findrepo.repogallery.ui.theme.colorPrimary
import com.findrepo.repogallery.ui.theme.colorSecondary

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBar(
    modifier: Modifier = Modifier,
    title: String = "",
    titleColor: Color = colorSecondary,
    backgroundColor: Color = colorBackground,
    notificationCount: Int = 0,
    showNavigationIcon: Boolean = true,
    onNavigationIconClick: () -> Unit = {},
    onNotificationClick: () -> Unit = {},
    showNotificationIcon: Boolean = true,
    showOtherAction: Boolean = false,
    @DrawableRes otherActionIconResId: Int = 0,
    @DrawableRes navigationIconResId: Int = 0,
    onOtherActionClick: () -> Unit = {},
    iconColor: Color = colorPrimary,
) {
    CenterAlignedTopAppBar(
        modifier = modifier
            .padding(top = 10.dp)
            .height(70.dp),
        title = {
            AppTextTitle(
                text = title,
                textAlign = TextAlign.Center,
                color = titleColor,
            )
        },
        navigationIcon = {
            if (showNavigationIcon) {
                Icon(
                    modifier = Modifier
                        .size(22.dp)
                        .clickable { onNavigationIconClick.invoke() },
                    painter = painterResource(id = navigationIconResId),
                    contentDescription = null,
                    tint = iconColor,
                )
            }
        },
        actions = {},
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors().copy(
            containerColor = backgroundColor,
        )
    )
}