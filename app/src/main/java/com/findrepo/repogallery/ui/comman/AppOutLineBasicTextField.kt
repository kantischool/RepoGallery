package com.findrepo.repogallery.ui.comman

import androidx.annotation.DrawableRes
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import com.findrepo.repogallery.ui.theme.colorBlack
import com.findrepo.repogallery.ui.theme.colorBorderBlack
import com.findrepo.repogallery.ui.theme.colorGrey
import com.findrepo.repogallery.ui.theme.colorWhite
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun AppOutlinedBasicTextField(
    modifier: Modifier = Modifier,
    text: String = "",
    placeholder: String = "",
    textStyle: TextStyle = MaterialTheme.typography.bodyMedium,
    fontSize: TextUnit = TextUnit(16f, TextUnitType.Sp),
    fontWeight: FontWeight = FontWeight.Medium,
    textAlign: TextAlign = TextAlign.Start,
    textColor: Color = colorBlack,
    placeHolderColor: Color = colorGrey,
    singleLine: Boolean = true,
    maxLines: Int = if (singleLine) 1 else Int.MAX_VALUE,
    minLines: Int = 1,
    readOnly: Boolean = false,
    enabled: Boolean = true,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    maxLength: Int = Int.MAX_VALUE,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    keyboardType: KeyboardType = KeyboardType.Text,
    imeAction: ImeAction = ImeAction.Next,
    isTextSetManually: Boolean = false,
    onValueChange: (String) -> Unit = {},
) {
    var textValue by remember { mutableStateOf(text) }

    Box(
        modifier = modifier
            .border(
                width = 0.1.dp,
                color = colorBorderBlack,
                shape = RoundedCornerShape(size = 3.dp),
            )
            .padding(8.dp),
        contentAlignment = Alignment.CenterStart,
    ) {
        BasicTextField(
            modifier = Modifier.fillMaxWidth(),
            value = if (isTextSetManually) text else textValue,
            onValueChange = {
                if (it.length <= maxLength) {
                    textValue = it
                    onValueChange(it)
                }
            },
            textStyle = textStyle.copy(
                color = textColor,
                textAlign = textAlign,
                fontSize = fontSize,
                fontWeight = fontWeight,
            ),
            cursorBrush = SolidColor(colorBlack),
            keyboardOptions = KeyboardOptions(
                keyboardType = keyboardType,
                imeAction = imeAction,
            ),
            keyboardActions = keyboardActions,
            singleLine = singleLine,
            maxLines = maxLines,
            minLines = minLines,
            readOnly = readOnly,
            enabled = enabled,
            visualTransformation = visualTransformation,
            decorationBox = { innerTextField ->
                val finalValue = if (isTextSetManually) text else textValue
                if (finalValue.isEmpty()) {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = placeholder,
                        style = textStyle,
                        fontWeight = fontWeight,
                        fontSize = fontSize,
                        color = placeHolderColor,
                        textAlign = textAlign,
                        maxLines = maxLines,
                        minLines = minLines,
                        overflow = TextOverflow.Ellipsis,
                    )
                }
                innerTextField()
            },
        )
    }
}


@Composable
fun AppFloatingActionButton(
    modifier: Modifier = Modifier,
    @DrawableRes resId: Int,
    iconTint: Color = colorBlack,
    backgroundColor: Color = colorWhite,
    radius: Dp = 8.dp,
    isLoading: Boolean = false,
    onClick: () -> Unit = {},
) {
    var isClickable by remember { mutableStateOf(true) }

    FloatingActionButton(
        modifier = modifier.size(35.dp),
        shape = RoundedCornerShape(radius),
        containerColor = backgroundColor,
        contentColor = backgroundColor,
        elevation = FloatingActionButtonDefaults.elevation(0.dp),
        onClick = {
            if (isClickable) {
                // Disable clicking to prevent multiple clicks
                isClickable = false
                onClick()
                // Coroutine to re-enable clicking after a delay
                CoroutineScope(Dispatchers.Main).launch {
                    delay(1000)
                    isClickable = true
                }
            }
        },
    ) {
        Box(contentAlignment = Alignment.Center) {
            Icon(
                modifier = Modifier
                    .size(16.dp)
                    .alpha(if (isLoading) 0f else 1f),
                painter = painterResource(id = resId),
                contentDescription = null,
                tint = iconTint,
            )
        }
    }
}