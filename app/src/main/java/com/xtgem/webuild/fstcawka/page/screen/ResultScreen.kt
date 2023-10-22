package com.xtgem.webuild.fstcawka.page.screen

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.Scaffold
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.gandiva.neumorphic.neu
import com.gandiva.neumorphic.shape.RoundedCorner
import com.xtgem.webuild.fstcawka.models.entities.DataResult
import com.xtgem.webuild.fstcawka.models.entities.Semester
import com.xtgem.webuild.fstcawka.models.entities.Student
import com.xtgem.webuild.fstcawka.models.entities.StudentBills
import com.xtgem.webuild.fstcawka.models.entities.StudentResult
import com.xtgem.webuild.fstcawka.models.enums.PaymentMethod
import com.xtgem.webuild.fstcawka.models.enums.Semesters
import com.xtgem.webuild.fstcawka.models.enums.Subjects
import com.xtgem.webuild.fstcawka.page.component.ErrorMessage
import com.xtgem.webuild.fstcawka.page.component.ErrorPage
import com.xtgem.webuild.fstcawka.page.component.InvalidSessionFrame
import com.xtgem.webuild.fstcawka.page.component.LoadingData
import com.xtgem.webuild.fstcawka.page.viewmodel.ResultScreenViewModel
import com.xtgem.webuild.fstcawka.page.viewmodel.ResultScreenViewModelFactory
import com.xtgem.webuild.fstcawka.ui.theme.custom.MyFonts
import com.xtgem.webuild.fstcawka.ui.theme.custom.defaultPressedNetAttrs
import java.util.UUID

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ResultScreen(userId: String, sessionToken: String,
                 subject: String, semester: String,
                 navController: NavController) {
    val viewModel: ResultScreenViewModel = viewModel(factory = ResultScreenViewModelFactory(userId, sessionToken, subject, semester))
    val result = remember { mutableStateOf(DataResult<List<StudentResult>>(isLoading = true)) }
    val student = remember { mutableStateOf(DataResult<Student>(isLoading = true)) }
    LaunchedEffect(Unit) {
        viewModel.results.observeForever { data ->
            result.value = data
        }
        viewModel.student.observeForever { student.value = it }
    }

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        modifier = Modifier.fillMaxSize(),
        topBar = { ResultHeader(student = student.value) }
    ) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Column(modifier = Modifier
                .fillMaxSize()
                .padding(it)) {
                when {
                    result.value.isLoading -> {
                        LoadingData()
                    }

                    result.value.error != null -> {
                        ErrorPage("News")
                    }

                    result.value.sessionInvalid == false -> {
                        InvalidSessionFrame()
                    }
                    else -> {
                        val data = result.value.data
                        if (data != null) {
                            ResultList(result = data, semester) {

                            }
                        }else {
                            ErrorMessage(
                                delay = 2000L,
                                message = "Oops. Profile Not Available",
                                load = true
                            )
                        }
                    }
                }
            }
        }
    }

}


@Composable
fun ResultList(result: List<StudentResult>, semester: String, onclickResult: (StudentResult) -> Unit) {
    val allResults = remember {
        mutableStateOf(result)
    }
    val semesters = remember {
        mutableStateOf(Semesters.valueOf(semester))
    }
    val chooseSemester = remember {
        mutableStateOf(false)
    }
    if (chooseSemester.value) {
        SemestersMenu(chooseSemester.value, Semesters.values()) { newSemester ->
            Log.d("illScreenViewMos", "$newSemester")
            allResults.value.forEach { Log.d("illScreenViewMos", "${it.semester}") }
            if (newSemester != null) {
                semesters.value = newSemester
                if (newSemester != Semesters.All) {
                    val newResults = result.filter { it.semester == newSemester }
                    allResults.value = newResults
                }else {
                    allResults.value = result
                }
            }
            chooseSemester.value = false
        }
    }
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(top = 30.dp, bottom = 16.dp, start = 16.dp, end = 16.dp)) {
        Column {
            val canChangeSemester = Semesters.valueOf(semester) == Semesters.All
            SemesterCell(semester = semesters.value, canChange = canChangeSemester) {
                chooseSemester.value = true
            }
        }

        Column(modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(top = 16.dp, bottom = 16.dp)) {
            ResultListCell(allResults.value) {
                onclickResult(it)
            }
        }
    }
}

@Composable
fun SemesterCell(semester: Semesters,
                 canChange: Boolean,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .padding(top = 5.dp, bottom = 5.dp, start = 5.dp, end = 5.dp),
        shape = RoundedCornerShape(corner = CornerSize(20.dp)),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 12.dp,
            pressedElevation = 0.dp,
            focusedElevation = 24.dp
        )
    ) {
        Row(modifier = Modifier.clickable(enabled = canChange) { onClick() }) {
            Column(
                modifier = Modifier
                    .weight(0.8f),
                horizontalAlignment = Alignment.Start
            ) {
                Row {
                    Column(
                        modifier = Modifier
                            .weight(0.5f)
                            .padding(top = 25.dp, bottom = 10.dp, start = 16.dp)
                    ) {
                        Text(
                            text = "Semester",
                            color = MaterialTheme.colorScheme.onBackground,
                            style = TextStyle(
                                fontWeight = FontWeight.Bold,
                                fontSize = 20.sp, fontFamily = MyFonts.customFontFamily[3]
                            )
                        )
                    }
                    Row(
                        modifier = Modifier
                            .weight(0.5f)
                            .padding(top = 20.dp, bottom = 10.dp, end = 5.dp),
                        horizontalArrangement = Arrangement.End,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(modifier = Modifier
                            .weight(if (canChange) 0.7f else 1f)
                            .padding(end = if (canChange) 3.dp else 16.dp),
                            horizontalAlignment = Alignment.End) {
                            val semesterSign = when (semester) {
                                Semesters.All -> "ALL"
                                Semesters.First -> "1"
                                Semesters.Second -> "2"
                                Semesters.Third -> "3"
                            }
                            Text(
                                text = semesterSign,
                                color = MaterialTheme.colorScheme.onBackground,
                                style = TextStyle(
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 20.sp, fontFamily = MyFonts.customFontFamily[3]
                                ))
                        }
                        if (canChange) {
                            Column(
                                modifier = Modifier
                                    .weight(0.3f)
                                    .fillMaxSize(),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Icon(
                                    Icons.Default.Menu, contentDescription = "Choose semester icon",
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(8.dp),
                                    tint = MaterialTheme.colorScheme.primary
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun ResultListCell(results: List<StudentResult>,
                 onClick: (StudentResult) -> Unit
) {
    Card(
        modifier = Modifier
            .wrapContentHeight()
            .padding(top = 5.dp, bottom = 5.dp),
        shape = RoundedCornerShape(corner = CornerSize(20.dp)),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 12.dp,
            pressedElevation = 0.dp,
            focusedElevation = 24.dp
        )
    ) {
        LazyColumn(modifier = Modifier
            .wrapContentHeight()) {
            items(results) { result ->
                val hideDivider = results.indexOf(result) != results.size - 1
                SingleResultCell(result, hideDivider = hideDivider) {
                    onClick(it)
                }
            }
        }
    }
}


@Composable
fun SingleResultCell(studentResult: StudentResult, hideDivider: Boolean, onClick: (StudentResult) -> Unit) {
    Column(modifier = Modifier
        .fillMaxWidth()
        .wrapContentHeight()
        .clickable { onClick(studentResult) }) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .height(45.dp)
            .padding(top = 16.dp, bottom = 5.dp, start = 16.dp, end = 16.dp)) {
            Column(modifier = Modifier.weight(0.5f),
                horizontalAlignment = Alignment.Start) {
                Text(
                    text = studentResult.subject.subjectName,
                    color = MaterialTheme.colorScheme.onBackground,
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp, fontFamily = MyFonts.customFontFamily[3]
                    ))
            }
            Column(modifier = Modifier
                .weight(0.5f)
                .fillMaxHeight(),
                horizontalAlignment = Alignment.End) {
                Text(
                    text = studentResult.totalScore.toString(),
                    color = MaterialTheme.colorScheme.onBackground,
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp, fontFamily = MyFonts.customFontFamily[3]
                    ))
            }
        }
        if (hideDivider) {
            Divider(modifier = Modifier
                .fillMaxWidth()
                .height(0.5.dp),
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f))
        }
    }
}

@Composable
fun SemestersMenu(open: Boolean,
                               paymentMethod: Array<Semesters>,
                               onClick: (Semesters?) -> Unit) {

    val semester = remember {
        mutableStateOf<Semesters?>(null)
    }

    DropdownMenu(expanded = open,
        onDismissRequest = {
            onClick(semester.value)
        },
        modifier = Modifier.fillMaxWidth()) {
        // bills
        paymentMethod.forEach { semester ->
            DropdownMenuItem(text = { Text(text = semester.name,
                style = MaterialTheme.typography.headlineSmall) },
                onClick = {
                    onClick(semester)
                })
        }
    }
}

@Composable
fun ResultHeader(student: DataResult<Student>) {
    Column(modifier = Modifier
        .fillMaxWidth()
        .height(80.dp)
        .padding(top = 16.dp, start = 16.dp, end = 16.dp)
        .background(MaterialTheme.colorScheme.background)
    ) {
        Row(modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End) {
            Column(modifier = Modifier.weight(0.6f),
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.Center) {
                Text(
                    text = if (student.data != null) "Results"
                    else if (student.isLoading) "Results Loading..." else if (student.sessionInvalid == false) "Results Error" else "",
                    color = MaterialTheme.colorScheme.onBackground,
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 25.sp, fontFamily = MyFonts.customFontFamily[0]
                    ))
            }

            Column(modifier = Modifier.weight(0.4f)) {
                if (student.data != null) {
                    Column(modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.End) {
                        Surface(
                            modifier = Modifier
                                .size(60.dp)
                                .padding(2.dp),
                            shape = CircleShape,
                            border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary)
                        ) {
                            AsyncImage(
                                model = student.data.imageLink,
                                contentDescription = null,
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .fillMaxSize()
                                    .clip(CircleShape)
                            )
                        }
                    }
                }
            }
        }
    }
}