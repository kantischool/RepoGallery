package com.findrepo.repogallery.ui.comman

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.unit.dp
import com.findrepo.repogallery.ui.theme.colorBlack
import com.findrepo.repogallery.ui.theme.colorTransparent

@Composable
fun AppLoader(
    color: Color = colorBlack,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorTransparent),
        contentAlignment = Alignment.Center,
    ) {
        CircularProgressIndicator(
            modifier = Modifier
                .size(50.dp)
                .padding(10.dp),
            strokeCap = StrokeCap.Butt,
            trackColor = colorTransparent,
            strokeWidth = 3.dp,
            color = color,
        )
    }
}