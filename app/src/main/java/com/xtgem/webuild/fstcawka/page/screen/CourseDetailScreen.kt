package com.xtgem.webuild.fstcawka.page.screen

import android.widget.Toast
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.ThumbDown
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.xtgem.webuild.fstcawka.models.entities.DataResult
import com.xtgem.webuild.fstcawka.models.enums.CourseReaction
import com.xtgem.webuild.fstcawka.models.relations.CourseAndReaction
import com.xtgem.webuild.fstcawka.page.component.ErrorMessage
import com.xtgem.webuild.fstcawka.page.component.ErrorPage
import com.xtgem.webuild.fstcawka.page.component.LoadingData
import com.xtgem.webuild.fstcawka.page.viewmodel.CourseDetailScreenViewModel
import com.xtgem.webuild.fstcawka.page.viewmodel.CourseDetailScreenViewModelFactory
import java.util.UUID

@Composable
fun CourseDetailScreen(courseId: String, studentId: String) {
    val viewModel: CourseDetailScreenViewModel = viewModel(factory = CourseDetailScreenViewModelFactory(courseId, studentId))
    val course = remember { mutableStateOf(DataResult<CourseAndReaction>(isLoading = true)) }
    LaunchedEffect(Unit) {
        viewModel.course.observeForever { data ->
            course.value = data
        }
    }
    val context = LocalContext.current
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
            course.value.isLoading -> {
                LoadingData()
            }
            course.value.error != null -> {
                ErrorPage("News")
            }
            else -> {
                val data = course.value.data
                if (data != null) {

                    Box(modifier = Modifier
                        .fillMaxSize(),
                        contentAlignment = Alignment.Center) {
                        AsyncImage(model = data.course.imageLink, contentDescription = null,
                            contentScale = ContentScale.Crop, modifier = Modifier
                                .fillMaxSize()
                                .blur(blur.value))
                        Column(modifier = Modifier
                            .fillMaxSize()
                            .padding(top = 16.dp),
                            verticalArrangement = Arrangement.Top,
                            horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(text = "E-Learning", fontSize = textSize.floatValue.toDouble().sp,
                                color = MaterialTheme.colorScheme.primary,
                                fontWeight = FontWeight.Bold,
                                style = MaterialTheme.typography.headlineLarge)
                        }
                        Column(modifier = Modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.Bottom) {
                            Column(modifier = Modifier
                                .fillMaxWidth()
                                .height(if (columHeight.value <= 30.dp) 30.dp else columHeight.value)
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
                                        .height(3.dp)
                                        .width(85.dp)
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

                                CourseDetailHeader(courses = data, isScrolling = scrollState, columHeight.value,
                                    courseId = courseId, studentId = studentId,
                                    onLikeClick = {
                                        // like the course
                                        val like = viewModel.react(like = true)
                                        if (like == true) {
                                            Toast.makeText(context, "Successfully Liked", Toast.LENGTH_SHORT).show()
                                        }
                                    }) {
                                    // dislike the course
                                    val like = viewModel.react(like = false)
                                    if (like == false) {
                                        Toast.makeText(context, "Successfully Disliked", Toast.LENGTH_SHORT).show()
                                    }
                                }
                                Divider(modifier = Modifier
                                    .animateContentSize(animationSpec = tween(durationMillis = 250))
                                    .height(if (scrollState.value > 0 && columHeight.value <= 735.dp) 0.dp else 0.5.dp),
                                    color = MaterialTheme.colorScheme.onBackground)
                                Column(modifier = Modifier
                                    .fillMaxWidth()
                                    .verticalScroll(
                                        enabled = columHeight.value > 30.dp,
                                        state = scrollState
                                    )
                                    .padding(start = 16.dp, end = 16.dp, top = 16.dp)) {
                                    val content = data.course.content.trimStart().trimEnd()
                                    Text(text = content)
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
fun CourseDetailHeader(courses: CourseAndReaction, isScrolling: ScrollState, columnHeight: Dp,
                       courseId: String, studentId: String,
                       onLikeClick: () -> Unit, onDisLikeClick: () -> Unit) {
    val voted = courses.reactions.find { it.reaction == CourseReaction.DisLike || it.reaction == CourseReaction.Like
            && it.courseId == UUID.fromString(courseId) && it.studentId == UUID.fromString(studentId) }
    fun dynamicHeight() = if (isScrolling.value > 0 && columnHeight <= 735.dp)
        Modifier.height(0.dp) else Modifier.heightIn()
    Column(modifier = dynamicHeight()
        .padding(start = 16.dp, end = 16.dp, bottom = 16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = courses.course.title, fontSize = 18.sp, fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.headlineLarge)
        Spacer(modifier = Modifier.height(3.dp))
        Row(modifier = Modifier
            .fillMaxWidth()) {
            Column(modifier = Modifier
                .weight(0.7f),
                horizontalAlignment = Alignment.Start) {
                Column(modifier = Modifier
                    .fillMaxWidth()
                    .heightIn()
                    .padding(5.dp),
                    horizontalAlignment = Alignment.Start) {
                    Icon(imageVector = Icons.Default.MenuBook, contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary)
                    Spacer(modifier = Modifier.width(5.dp))
                    Text(text = "${courses.course.readAmount}", fontSize = 13.sp,
                        color = MaterialTheme.colorScheme.primary,
                        style = MaterialTheme.typography.headlineSmall)
                }
            }

            Column(modifier = Modifier
                .weight(0.15f)
                .padding(end = 10.dp),
                horizontalAlignment = Alignment.End) {
                val liked = courses.reactions.find { it.reaction == CourseReaction.Like
                        && it.courseId == UUID.fromString(courseId) && it.studentId == UUID.fromString(studentId) }
                Column(modifier = Modifier
                    .fillMaxWidth()
                    .heightIn()
                    .padding(5.dp)
                    .clickable(
                        enabled = voted == null
                    ) {
                        onLikeClick()
                    },
                    horizontalAlignment = Alignment.End) {
                    Icon(imageVector = Icons.Default.ThumbUp, contentDescription = null,
                        tint = if (liked == null) MaterialTheme.colorScheme.onBackground else MaterialTheme.colorScheme.primary)
                    Spacer(modifier = Modifier.width(5.dp))
                    Text(text = "${courses.course.likedAmount}", fontSize = 13.sp,
                        color = if (liked == null) MaterialTheme.colorScheme.onBackground else MaterialTheme.colorScheme.primary,
                        style = MaterialTheme.typography.headlineSmall)
                }
            }

            Column(modifier = Modifier.weight(0.15f),
                horizontalAlignment = Alignment.End) {
                val disliked = courses.reactions.find { it.reaction == CourseReaction.DisLike
                        && it.courseId == UUID.fromString(courseId) && it.studentId == UUID.fromString(studentId) }
                Column(modifier = Modifier
                    .fillMaxWidth()
                    .heightIn()
                    .padding(5.dp)
                    .clickable(
                        enabled = voted == null
                    ) {
                        onDisLikeClick()
                    },
                    horizontalAlignment = Alignment.End) {
                    Icon(imageVector = Icons.Default.ThumbDown, contentDescription = null,
                        tint = if (disliked == null) MaterialTheme.colorScheme.onBackground else MaterialTheme.colorScheme.primary)
                    Spacer(modifier = Modifier.width(5.dp))
                    Text(text = "${courses.course.dislikeAmount}", fontSize = 13.sp,
                        color = if (disliked == null) MaterialTheme.colorScheme.onBackground else MaterialTheme.colorScheme.primary,
                        style = MaterialTheme.typography.headlineSmall)
                }
            }
        }
    }
}
