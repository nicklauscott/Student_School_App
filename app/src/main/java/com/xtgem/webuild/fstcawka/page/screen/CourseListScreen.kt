package com.xtgem.webuild.fstcawka.page.screen


import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.gandiva.neumorphic.neu
import com.xtgem.webuild.fstcawka.models.entities.Courses
import com.xtgem.webuild.fstcawka.models.entities.DataResult
import com.xtgem.webuild.fstcawka.models.enums.Screens
import com.xtgem.webuild.fstcawka.page.component.ErrorMessage
import com.xtgem.webuild.fstcawka.page.component.ErrorPage
import com.xtgem.webuild.fstcawka.page.component.LoadingData
import com.xtgem.webuild.fstcawka.page.viewmodel.CourseListScreenViewModel
import com.xtgem.webuild.fstcawka.ui.theme.custom.defaultPressedNetAttrs

@Composable
fun CourseListScreen(studentId: String = "", navController: NavController) {
    val viewModel = viewModel<CourseListScreenViewModel>()
    val course = remember { mutableStateOf(DataResult<List<Courses>>(isLoading = true)) }
    LaunchedEffect(Unit) {
        viewModel.course.observeForever { data ->
            course.value = data
        }
    }
    Surface(modifier = Modifier
        .fillMaxSize()
        .background(MaterialTheme.colorScheme.background)
        ) {
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
                    LazyColumn(modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 30.dp, start = 16.dp, end = 16.dp)) {
                        item {
                            Text(text = "E-Learning", fontSize = 34.sp,
                                color = MaterialTheme.colorScheme.onBackground,
                                fontWeight = FontWeight.Bold,
                                style = MaterialTheme.typography.headlineLarge)
                        }

                        items(data){ course ->
                            CourseCell(course) {
                                navController.navigate(route = Screens.CourseDetail.withArg(it.courseId.toString(), studentId))
                            }
                        }
                    }
                }else {
                    ErrorMessage(delay = 2000L, message = "Oops. Courses Not Available", load = true)
                }
            }
        }
    }
}

@Composable
fun CourseCell(course: Courses, onClick: (Courses) -> Unit) {
    Column(modifier = Modifier
        .fillMaxWidth()
        .height(150.dp)) {
        Surface(modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
            .clickable {
                onClick(course)
            },
            shape = RoundedCornerShape(18.dp),
            border = BorderStroke(width = 1.dp,
                color = MaterialTheme.colorScheme.onBackground)
        ) {
            Row(modifier = Modifier
                .fillMaxSize()
                .padding(5.dp)
            ) {
                Row(modifier = Modifier
                    .weight(0.7f)
                    .fillMaxHeight()) {
                    Column(modifier = Modifier
                        .weight(0.4f)
                        .fillMaxHeight()
                        .padding(10.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally) {
                        AsyncImage(model = course.imageLink, contentDescription = null,
                            contentScale = ContentScale.Crop, modifier = Modifier
                                .fillMaxSize()
                                .clip(RoundedCornerShape(12.dp)))
                    }
                    Column(modifier = Modifier
                        .weight(0.6f)
                        .fillMaxHeight()
                        .padding(start = 10.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.Start) {
                        Text(text = course.title, fontSize = 23.sp,
                            color = MaterialTheme.colorScheme.onBackground,
                            fontWeight = FontWeight.Bold,
                            style = MaterialTheme.typography.headlineLarge)
                    }
                }

                Column(modifier = Modifier
                    .weight(0.3f)
                    .fillMaxHeight()
                    .padding(10.dp)) {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .neu(defaultPressedNetAttrs()),
                        shape = RoundedCornerShape(24.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = Color.Transparent
                        )
                    ){
                        Column(modifier = Modifier
                            .fillMaxSize()
                            .padding(5.dp)
                            .clip(shape = RoundedCornerShape(corner = CornerSize(24.dp))),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(text = course.likedAmount.toString(), fontSize = 35.sp,
                                color = MaterialTheme.colorScheme.primary,
                                fontWeight = FontWeight.Bold,
                                style = MaterialTheme.typography.headlineMedium)
                            Text(text = "Likes", fontSize = 23.sp,
                                color = MaterialTheme.colorScheme.onBackground,
                                fontWeight = FontWeight.Light,
                                style = MaterialTheme.typography.headlineSmall)
                        }
                    }
                }
            }
        }
    }
}