package com.findrepo.repogallery.ui.comman

import androidx.annotation.DrawableRes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import coil.compose.AsyncImage
import com.findrepo.repogallery.R

@Composable
fun AppNetworkImage(
    modifier: Modifier = Modifier,
    model: Any = "",
    @DrawableRes placeholder: Int = R.drawable.img_placeholder,
    @DrawableRes error: Int = R.drawable.img_placeholder,
    contentScale: ContentScale = ContentScale.Fit,
) {
    var isImageLoaded by remember { mutableStateOf(false) }

    Box(modifier = modifier) {
        AsyncImage(
            modifier = Modifier.fillMaxSize(),
            model = model,
            placeholder = painterResource(id = placeholder),
            error = painterResource(id = error),
            contentDescription = null,
            contentScale = contentScale,
            onLoading = { isImageLoaded = false },
            onSuccess = { isImageLoaded = true },
            onError = { isImageLoaded = true },
        )
    }
}