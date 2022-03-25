package com.amegane3231.videosearcher.android.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.amegane3231.videosearcher.android.R
import com.amegane3231.videosearcher.android.custom.RectTransformation
import com.amegane3231.videosearcher.android.ui.theme.Black
import com.amegane3231.videosearcher.android.ui.theme.Gray300
import com.amegane3231.videosearcher.android.ui.theme.Typography
import com.amegane3231.videosearcher.android.ui.theme.White
import com.amegane3231.videosearcher.android.ui.theme.WhiteDark
import com.amegane3231.videosearcher.flux.search.action.GetVideoDataAction
import com.google.accompanist.insets.navigationBarsPadding
import com.google.accompanist.insets.statusBarsPadding

@OptIn(ExperimentalMaterialApi::class, coil.annotation.ExperimentalCoilApi::class)
@Composable
fun VideoDetailBottomSheetContent(getVideoDataState: GetVideoDataAction) {
    when (getVideoDataState) {
        is GetVideoDataAction.FetchDataFailed -> {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxSize()
            ) {
                Text(text = stringResource(R.string.msg_failed_data), textAlign = TextAlign.Center)
            }
        }
        is GetVideoDataAction.FetchDataWaiting, is GetVideoDataAction.Standby -> {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
                    .statusBarsPadding()
                    .navigationBarsPadding()
            ) {
                CircularProgressIndicator()
            }
        }
        is GetVideoDataAction.FetchDataSucceeded -> {
            val videoDetail = getVideoDataState.data.videoDetailList[0]

            val videoId = videoDetail.videoId

            val title = videoDetail.title

            val description = videoDetail.description

            val imageUrl = videoDetail.imageUrl

            val annotatedString = buildAnnotatedString {
                val link = "https://www.youtube.com/watch?v=$videoId"

                pushStringAnnotation(tag = "URL", annotation = link)

                withStyle(SpanStyle(color = Black, textDecoration = null)) {
                    append("Youtubeアプリで開く")
                }
            }

            val urlHandler = LocalUriHandler.current

            val painter = rememberImagePainter(data = imageUrl, builder = {
                with(LocalDensity.current) {
                    transformations(
                        RectTransformation(
                            left = 0f,
                            top = 0f,
                            right = LocalConfiguration.current.screenWidthDp.dp.toPx(),
                            bottom = LocalConfiguration.current.screenWidthDp.dp.toPx()
                        )
                    )
                }
            })

            val componentModifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
                .background(White)

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .statusBarsPadding()
                    .navigationBarsPadding()
                    .background(Gray300)
                    .verticalScroll(rememberScrollState())
            ) {
                Column(modifier = componentModifier) {
                    Image(
                        painter = painter,
                        contentDescription = null,
                        modifier = Modifier
                            .width(LocalConfiguration.current.screenWidthDp.dp)
                            .height((LocalConfiguration.current.screenWidthDp * 9 / 16).dp)
                    )
                    Text(
                        text = title,
                        style = Typography.h6,
                        modifier = Modifier.padding(vertical = 16.dp)
                    )
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = componentModifier
                        .height(56.dp)
                        .clickable {
                            annotatedString
                                .getStringAnnotations("URL", 0, annotatedString.length)
                                .firstOrNull()
                                ?.let { annotated ->
                                    urlHandler.openUri(annotated.item)
                                }
                        }
                ) {
                    Text(
                        text = stringResource(R.string.link),
                        color = WhiteDark,
                        modifier = Modifier.padding(start = 16.dp, end = 12.dp)
                    )
                    ClickableText(text = annotatedString, style = Typography.body1) {
                        annotatedString.getStringAnnotations("URL", it, it)
                            .firstOrNull()?.let { annotated ->
                                urlHandler.openUri(annotated.item)
                            }
                    }
                    Row(
                        horizontalArrangement = Arrangement.End,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(end = 16.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_arrow_forward),
                            contentDescription = null,
                            tint = WhiteDark
                        )
                    }
                }

                Text(
                    text = description,
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(White)
                        .padding(top = 16.dp)
                )
            }
        }
    }
}
