package com.findrepo.utility.util

import androidx.compose.animation.core.EaseOutSine
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally

val slideInHorizontallyTransition = slideInHorizontally(
    initialOffsetX = { it },
    animationSpec = tween(durationMillis = 200, easing = LinearEasing),
)

val slideOutHorizontallyTransition = slideOutHorizontally(
    targetOffsetX = { it },
    animationSpec = tween(durationMillis = 200, easing = LinearEasing),
)

val fadeInTransition = fadeIn(
    animationSpec = tween(durationMillis = 500),
)

val fadeOutTransition = fadeOut(
    animationSpec = tween(durationMillis = 500),
)

val slideEnterTransition = slideInHorizontally(
    initialOffsetX = { it },
    animationSpec = tween(durationMillis = 500, easing = LinearOutSlowInEasing),
)

val slideExitTransition = slideOutHorizontally(
    targetOffsetX = { -it },
    animationSpec = tween(durationMillis = 500, easing = LinearOutSlowInEasing),
)

val fadeEnterTransition = fadeIn(
    animationSpec = tween(durationMillis = 500, easing = EaseOutSine),
)

val fadeExitTransition = fadeOut(
    animationSpec = tween(durationMillis = 500, easing = EaseOutSine),
)