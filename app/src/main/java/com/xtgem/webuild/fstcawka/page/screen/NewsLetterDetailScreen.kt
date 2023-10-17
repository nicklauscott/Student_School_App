package com.xtgem.webuild.fstcawka.page.screen

import android.util.Log
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.xtgem.webuild.fstcawka.misc.Uncategorized
import com.xtgem.webuild.fstcawka.models.entities.DataResult
import com.xtgem.webuild.fstcawka.models.entities.News
import com.xtgem.webuild.fstcawka.page.component.ErrorMessage
import com.xtgem.webuild.fstcawka.page.component.ErrorPage
import com.xtgem.webuild.fstcawka.page.component.LoadingData
import com.xtgem.webuild.fstcawka.page.viewmodel.NewsLetterDetailScreenViewModel
import com.xtgem.webuild.fstcawka.page.viewmodel.NewsLetterDetailScreenViewModelFactory
import com.xtgem.webuild.fstcawka.ui.theme.custom.MyFonts

@Composable
fun NewsLetterDetailScreen(newsId: String) {
    val viewModel: NewsLetterDetailScreenViewModel = viewModel(factory = NewsLetterDetailScreenViewModelFactory(newsId))
    val news = remember { mutableStateOf(DataResult<News>(isLoading = true)) }
    LaunchedEffect(Unit) {
        viewModel.news.observeForever { data ->
            news.value = data
        }
    }
    val columHeight = remember {
        mutableStateOf(600.dp)
    }

    val scrollState = rememberScrollState()

    val blur = remember {
        mutableStateOf(8.dp)
    }
    val textSize = remember {
        mutableFloatStateOf(35f)
    }

    Surface(modifier = Modifier
        .fillMaxSize()
        .background(MaterialTheme.colorScheme.background)) {
        when {
            news.value.isLoading -> {
                LoadingData()
            }
            news.value.error != null -> {
                ErrorPage("News")
            }
            else -> {
                val data = news.value.data
                if (data != null) {

                    Box(modifier = Modifier
                        .fillMaxSize(),
                        contentAlignment = Alignment.Center) {
                        AsyncImage(model = data.imageLink, contentDescription = null,
                            contentScale = ContentScale.Crop, modifier = Modifier
                                .fillMaxSize()
                                .blur(blur.value))
                        Column(modifier = Modifier
                            .fillMaxSize()
                            .padding(top = 16.dp),
                            verticalArrangement = Arrangement.Top,
                            horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(text = data.category.newsName, fontSize = textSize.floatValue.toDouble().sp,
                                color = MaterialTheme.colorScheme.primary,
                                fontWeight = FontWeight.Bold,
                                style = MaterialTheme.typography.headlineLarge)
                        }
                        Column(modifier = Modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.Bottom) {
                            Column(modifier = Modifier
                                .fillMaxWidth()
                                .height(if (columHeight.value <= 100.dp) 100.dp else columHeight.value)   /////
                                .clip(
                                    if (columHeight.value > 830.dp) RoundedCornerShape(
                                        topStart = 0.dp,
                                        topEnd = 0.dp
                                    )
                                    else RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)
                                )
                                .background(MaterialTheme.colorScheme.background)
                                .padding(top = 8.dp),
                            ) {
                                Column(modifier = Modifier
                                    .fillMaxWidth(),
                                    horizontalAlignment = Alignment.CenterHorizontally) {
                                    Divider(modifier = Modifier
                                        .height(3.5.dp)
                                        .width(100.dp)
                                        .clip(RoundedCornerShape(CornerSize(4.dp)))
                                        .pointerInput(Unit) {
                                            detectDragGestures { _, dragAmount ->
                                                columHeight.value -= dragAmount.y.toDp()
                                                blur.value += dragAmount.y.toDp()
                                                textSize.floatValue += dragAmount.y / 40
                                            }
                                        },
                                        color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f))
                                }
                                Spacer(modifier = Modifier.height(12.dp))

                                NewsDetailHeader(news = data, isScrolling = scrollState, columHeight.value)
                                Divider(modifier = Modifier
                                    .animateContentSize(animationSpec = tween(durationMillis = 250))
                                    .height(if (scrollState.value > 0
                                        && columHeight.value <= 735.dp && columHeight.value >= 100.dp) 0.dp else 0.5.dp),
                                    color = MaterialTheme.colorScheme.onBackground)
                                Column(modifier = Modifier
                                    .fillMaxWidth()
                                    .verticalScroll(
                                        enabled = columHeight.value > 30.dp,
                                        state = scrollState
                                    )
                                    .padding(start = 16.dp, end = 16.dp, top = 16.dp)) {
                                    val content = data.content.trimStart().trimEnd()
                                    val annotatedString = buildAnnotatedString {
                                        withStyle(
                                            style = SpanStyle(
                                                color = MaterialTheme.colorScheme.primary,
                                                fontWeight = FontWeight.Light,
                                                fontSize = 25.sp, fontFamily = MyFonts.customFontFamily[0]
                                            )
                                        ) {
                                            append(content.first())
                                        }
                                        withStyle(
                                            style = SpanStyle(
                                                color = MaterialTheme.colorScheme.onBackground,
                                                fontWeight = FontWeight.Light,
                                                fontSize = 13.sp, fontFamily = MyFonts.customFontFamily[5]
                                            )
                                        ) {
                                            append(content.substring(1))
                                        }
                                    }
                                    Text(text = annotatedString)
                                }
                            }
                        }
                    }

                }else {
                    ErrorMessage(delay = 2000L, message = "Oops. News Not Available", load = true)
                }
            }
        }
    }
}


@Composable
fun NewsDetailHeader(news: News, isScrolling: ScrollState, columnHeight: Dp) {
    Log.d("NewsDetailHeader", "columnHeight: $columnHeight")
    fun dynamicHeight() = if (isScrolling.value > 0 && columnHeight <= 735.dp
        && columnHeight >= 100.dp)
        Modifier.height(0.dp) else Modifier.heightIn()
    Column(modifier = dynamicHeight()
        .padding(start = 16.dp, end = 16.dp, bottom = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = news.description, fontSize = 18.sp, fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.headlineLarge)
        Spacer(modifier = Modifier.height(3.dp))
        Column(modifier = Modifier
            .fillMaxWidth(),
            horizontalAlignment = Alignment.End) {
            Text(text = Uncategorized().simpleDateFormatter(news.createdDate), fontSize = 13.sp,
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.headlineSmall)
        }
    }
}
