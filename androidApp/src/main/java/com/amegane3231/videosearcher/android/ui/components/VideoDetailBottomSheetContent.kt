package com.amegane3231.videosearcher.android.ui.components

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.amegane3231.videosearcher.android.ui.theme.Typography
import com.google.accompanist.insets.navigationBarsPadding
import com.google.accompanist.insets.statusBarsPadding

@OptIn(ExperimentalMaterialApi::class, coil.annotation.ExperimentalCoilApi::class)
@Composable
fun VideoDetailBottomSheetContent(
    videoId: String,
    title: String,
    detail: String,
    imageUrl: String
) {
    val annotatedString = buildAnnotatedString {
        val link = "https://www.youtube.com/watch?v=$videoId"

        val startIndex = 0

        val endIndex = link.length

        append(link)
        addStyle(
            style = SpanStyle(
                color = Color.Blue,
                textDecoration = TextDecoration.Underline
            ),
            start = startIndex, end = endIndex
        )
        addStringAnnotation(
            tag = "URL",
            annotation = link,
            start = startIndex,
            end = endIndex
        )
    }

    val urlHandler = LocalUriHandler.current

    val painter = rememberImagePainter(data = imageUrl)

    Log.d("IMAGE", "${painter.state}")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .navigationBarsPadding()
            .verticalScroll(rememberScrollState())
    ) {
        Image(
            painter = painter,
            contentDescription = null,
            modifier = Modifier.width(LocalConfiguration.current.screenWidthDp.dp)
                .height((LocalConfiguration.current.screenWidthDp * 9 / 16).dp)
        )
        Text(text = title, style = Typography.h6)

        Spacer(modifier = Modifier.height(56.dp))

        Text(text = "【リンク】", style = Typography.h6)

        ClickableText(text = annotatedString, style = Typography.body1) {
            annotatedString.getStringAnnotations("URL", it, it)
                .firstOrNull()?.let { annotated ->
                    urlHandler.openUri(annotated.item)
                }
        }

        Spacer(modifier = Modifier.height(56.dp))

        Text(text = detail)
    }
}
