package com.xtgem.webuild.fstcawka.page.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.gandiva.neumorphic.neu
import com.gandiva.neumorphic.shape.RoundedCorner
import com.xtgem.webuild.fstcawka.R
import com.xtgem.webuild.fstcawka.models.entities.DataResult
import com.xtgem.webuild.fstcawka.models.entities.Student
import com.xtgem.webuild.fstcawka.models.enums.Screens
import com.xtgem.webuild.fstcawka.models.enums.Semesters
import com.xtgem.webuild.fstcawka.models.enums.Subjects
import com.xtgem.webuild.fstcawka.page.component.NavFooter
import com.xtgem.webuild.fstcawka.page.viewmodel.HomeScreenViewModel
import com.xtgem.webuild.fstcawka.page.viewmodel.HomeScreenViewModelFactory
import com.xtgem.webuild.fstcawka.page.widget.CustomButton1
import com.xtgem.webuild.fstcawka.ui.theme.custom.MyFonts
import com.xtgem.webuild.fstcawka.ui.theme.custom.Theme
import com.xtgem.webuild.fstcawka.ui.theme.custom.defaultFlatNeuAttrs
import com.xtgem.webuild.fstcawka.ui.theme.custom.defaultPressedNetAttrs

@Preview
@Composable
fun PreviewHomeScreen() {
    Theme {
        HomeScreen()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController = rememberNavController(),
               userId: String = "", sessionToken: String = "") {
    val viewModel: HomeScreenViewModel = viewModel(factory = HomeScreenViewModelFactory(userId, sessionToken))
    val student = remember { mutableStateOf(DataResult<Student>(isLoading = true)) }
    val greeting = remember { mutableStateOf(DataResult<String>(isLoading = true)) }
    LaunchedEffect(Unit) {
        viewModel.student.observeForever { student.value = it }
        viewModel.greeting.observeForever { greeting.value = it }
    }
    val context = LocalContext.current
    val displayMetrics = context.resources.displayMetrics
    val width = displayMetrics.widthPixels
    val height = displayMetrics.heightPixels

    Scaffold(modifier = Modifier
        .fillMaxSize()
        .background(color = MaterialTheme.colorScheme.background),
        bottomBar = {
            NavFooter(Screens.Home,
                onNewsClick = {
                    // go to news screen
                              navController.navigate(Screens.NewsLetter.withArg(userId, sessionToken))
                },
                onProfileClick = {
                    // go to profile screen
                    navController.navigate(Screens.Profile.withArg(userId, sessionToken))
            })
        }
        ) {
        it.calculateBottomPadding()
        // UI
        Surface(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(color = MaterialTheme.colorScheme.background)) {
            Column {
                student.value.data?.let { it1 -> Header(student = it1, greeting = greeting.value.data,
                    width = width, height = height) }
                Column(modifier = Modifier
                    .fillMaxWidth()
                    .background(color = MaterialTheme.colorScheme.background)) {
                    Column(modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 20.dp),
                        horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(text = "Student DashBord", style = MaterialTheme.typography.headlineMedium)
                    }

                    Spacer(modifier = Modifier.padding(top = 10.dp))

                    CellRow(cellOne = {
                        val newsLetterIcon = painterResource(id = R.drawable.ic_newsletter)
                        Cell(
                            label = "News Letter",
                            icon = newsLetterIcon
                        ) {
                            navController.navigate(Screens.NewsLetter.withArg(userId, sessionToken))
                        }
                    }) {
                        val newsLetterIcon = painterResource(id = R.drawable.ic_e_learning)
                        Cell(
                            label = "E-Learning",
                            icon = newsLetterIcon
                        ) {
                            navController.navigate(route = Screens.Course.withArg(userId))
                        }
                    }

                    CellRow(cellOne = {
                        val newsLetterIcon = painterResource(id = R.drawable.ic_news)
                        Cell(
                            label = "News",
                            icon = newsLetterIcon
                        ) {
                            navController.navigate(Screens.NewsLetter.withArg(userId, sessionToken))
                        }
                    }) {
                        val newsLetterIcon = painterResource(id = R.drawable.ic_assignment)
                        Cell(
                            label = "Assignment",
                            icon = newsLetterIcon
                        ) {
                            navController.navigate(Screens.Assignment.withArg(userId, sessionToken))
                        }
                    }

                    CellRow(cellOne = {
                        val newsLetterIcon = painterResource(id = R.drawable.ic_article)
                        Cell(
                            label = "Articles",
                            icon = newsLetterIcon
                        ) {
                            navController.navigate(Screens.NewsLetter.withArg(userId, sessionToken))
                        }
                    }) {
                        val newsLetterIcon = painterResource(id = R.drawable.ic_bills)
                        Cell(
                            label = "School Bill",
                            icon = newsLetterIcon
                        ) {
                            navController.navigate(Screens.Bill.withArg(userId, sessionToken))
                        }
                    }

                    Column(modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 20.dp),
                        horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(text = "Check Results", fontSize = 30.sp, style = MaterialTheme.typography.headlineMedium)
                    }

                    CheckResult { resultOf ->
                        if (resultOf.semester != null && resultOf.subject != null) {
                            navController.navigate(Screens.Result.withArg(
                                userId, sessionToken, resultOf.subject.name, resultOf.semester.name))
                        }
                    }
                }
            }

        }
    }
}



data class SpinnerHelper(
    val subject: Subjects? = null,
    val semester: Semesters? = null,
    val resultOf: SpinnerHelper? = null
)

@Composable
fun CheckResult(onClick: (SpinnerHelper) -> Unit) {
    Column(modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 12.dp)) {

        val spinnerHelper = remember {
            mutableStateOf(SpinnerHelper())
        }

        val isSubjectEmpty = remember {
            mutableStateOf(false)
        }

        val isSemesterEmpty = remember {
            mutableStateOf(false)
        }


        // Subject
        Column(modifier = Modifier.padding(bottom = 10.dp)) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp)
                    .neu(
                        defaultPressedNetAttrs(
                            cornerShape = RoundedCorner(14.dp)
                        )
                    ),
                shape = RoundedCornerShape(24.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.background
                )
            ){
                Column(modifier = Modifier
                    .clip(shape = RoundedCornerShape(corner = CornerSize(24.dp)))
                    .border(
                        width = if (isSubjectEmpty.value) 1.5.dp else 0.dp,
                        color = if (isSubjectEmpty.value) Color.Red else Color.Transparent
                    )) {
                    SpinnerPicker(label = "Select Subject",
                        subjects = Subjects.values()) {
                        spinnerHelper.value = spinnerHelper.value.copy(subject = it.subject)
                        isSubjectEmpty.value = false
                    }
                }
            }
        }

        // Semester
        Column(modifier = Modifier.padding(bottom = 16.dp)) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp)
                    .neu(defaultPressedNetAttrs()),
                shape = RoundedCornerShape(24.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.background
                )
            ){
                Column(modifier = Modifier
                    .clip(shape = RoundedCornerShape(corner = CornerSize(24.dp)))
                    .border(
                        width = if (isSemesterEmpty.value) 1.5.dp else 0.dp,
                        color = if (isSemesterEmpty.value) Color.Red else Color.Transparent
                    )) {
                    SpinnerPicker(label = "Select Semester",
                        semesters = Semesters.values()) {
                        spinnerHelper.value = spinnerHelper.value.copy(semester = it.semester)
                        isSemesterEmpty.value = false
                    }
                }
            }
        }

        // Check Button
        Column(modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)) {
                val buttonModifier = Modifier.fillMaxHeight()
                CustomButton1(buttonText = "View Result",
                    modifier = buttonModifier) {
                    if (spinnerHelper.value.subject == null) isSubjectEmpty.value = true
                    if (spinnerHelper.value.semester == null) isSemesterEmpty.value = true
                    if (spinnerHelper.value.subject != null
                        && spinnerHelper.value.semester != null) {
                        // check for result
                        onClick(SpinnerHelper(subject = spinnerHelper.value.subject, semester = spinnerHelper.value.semester))
                    }
                }
        }

        Spacer(modifier = Modifier.height(300.dp))
    }
}

@Composable
fun SpinnerPicker(label: String, subjects: Array<Subjects>? = null,
                  semesters: Array<Semesters>? = null,
                  onClick: (SpinnerHelper) -> Unit) {
    val expanded = remember {
        mutableStateOf(false)
    }

    val selectedItem = remember {
        mutableStateOf(label)
    }

    Column {
        Row(modifier = Modifier
            .fillMaxSize()
            .padding(start = 10.dp, end = 10.dp)
            .clickable { expanded.value = true }) {
            Column(modifier = Modifier
                .weight(0.7f)
                .fillMaxHeight(),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Center) {
                Text(text = selectedItem.value, fontSize = 18.sp,
                    style = MaterialTheme.typography.bodyMedium, fontFamily = MyFonts.customFontFamily[3])
            }
            Column(modifier = Modifier
                .weight(0.3f)
                .fillMaxHeight(),
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.Center) {
                Icon(imageVector = Icons.Default.ArrowDropDown,
                    contentDescription = "Show more")
            }
        }

        }
        DropdownMenu(expanded = expanded.value,
            onDismissRequest = { expanded.value = false },
            modifier = Modifier.fillMaxWidth()) {
            // subjects
            subjects?.forEach { subject ->
                DropdownMenuItem(text = { Text(text = subject.subjectName,
                    style = MaterialTheme.typography.headlineSmall) },
                    onClick = {
                        selectedItem.value = subject.name
                        expanded.value = false
                        onClick(
                            SpinnerHelper(
                                subject = Subjects.valueOf(selectedItem.value)
                            )
                        )
                    })
            }

            // semesters
            semesters?.forEach { semester ->
                DropdownMenuItem(text = { Text(text = semester.name,
                    style = MaterialTheme.typography.headlineSmall) },
                    onClick = {
                        selectedItem.value = semester.name
                        expanded.value = false
                        onClick(
                            SpinnerHelper(
                                semester = Semesters.valueOf(selectedItem.value)
                            )
                        )
                    })
            }

        }
    }



@Composable
fun CellRow(cellOne: @Composable () -> Unit, cellTwo: @Composable () -> Unit) {
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(top = 8.dp)) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(top = 5.dp)) {
            Column(modifier = Modifier
                .weight(0.5f)
                .padding(start = 16.dp, end = 5.dp)) {
                cellOne()
            }

            Column(modifier = Modifier
                .weight(0.5f)
                .padding(start = 5.dp, end = 16.dp)) {
                cellTwo()
            }
        }
    }
}


@Composable
fun Cell(
    label: String = "",
    icon: Painter = painterResource(id = R.drawable.ic_news),
    onClick: () -> Unit
) {
    Card(modifier = Modifier
        .fillMaxWidth()
        .height(85.dp)
        .neu(defaultFlatNeuAttrs())
        .clickable { onClick() },
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background
        )) {
        Column(modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Transparent)
            .padding(top = 16.dp, bottom = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center) {
            Column(modifier = Modifier.weight(0.5f)) {
                Image(
                    painter = icon,
                    contentDescription = label,
                    modifier = Modifier.size(25.dp),
                    contentScale = ContentScale.Crop
                )
            }
            Spacer(modifier = Modifier.height(5.dp))
            Column(modifier = Modifier.weight(0.5f)) {
                Text(text = label, style = MaterialTheme.typography.headlineSmall)
            }
        }
    }
}

@Composable
fun Header(student: Student, greeting: String?,
           width: Int, height: Int) {
    val circleColor =  MaterialTheme.colorScheme.primary
    Row(modifier = Modifier
        .fillMaxWidth()
        .height(125.dp)
        .background(color = MaterialTheme.colorScheme.tertiary)) {
        Column(modifier = Modifier
            .weight(0.7f)
            .padding(top = 40.dp, start = 16.dp)
            .drawBehind {
                // width: 720 => height: 1440
                //width: 1080 => height: 2285
                /*

                if (width <= 720 && height <= 1440) Offset(350f, -500f)
                                else if (width <= 1080 && height <= 2285) Offset(
                                    550f,
                                    -500f
                                ) else Offset(750f, -500f),
                 */
                drawCircle(
                    color = circleColor,
                    center = if (width <= 720 && height <= 1440) Offset(40f, 20f)
                    else if (width <= 1080 && height <= 2285) Offset(60f, 20f) else  Offset(80f, 20f),
                    radius = if (width <= 720 && height <= 1440) 210f else if (width <= 1080 && height <= 2285) 310f
                    else 410f
                )
            }
        ) {
            Text(text = "$greeting ${student.firstName}",
                color = MaterialTheme.colorScheme.onBackground,
                style = TextStyle(fontWeight = FontWeight.Bold,
                    fontSize = 25.sp, fontFamily = MyFonts.customFontFamily[0])
                )

            Spacer(modifier = Modifier.height(10.dp))

            Column(modifier = Modifier
                .clip(shape = RoundedCornerShape(corner = CornerSize(30.dp)))
                .background(color = MaterialTheme.colorScheme.secondary)
                .width(180.dp)
                .padding(start = 10.dp, top = 8.dp, bottom = 8.dp, end = 16.dp)
            ) {
                val annotatedString = buildAnnotatedString {
                    withStyle(style = SpanStyle(
                        color = MaterialTheme.colorScheme.onBackground,
                        fontWeight = FontWeight.Light,
                        fontSize = 16.sp, fontFamily = MyFonts.customFontFamily[3]
                    )) {
                        append("REG NO:   ")
                    }
                    withStyle(style = SpanStyle(
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.Light,
                        fontSize = 16.sp, fontFamily = MyFonts.customFontFamily[3]
                    )) {
                        append(student.regId)
                    }
                }
                
                Text(text = annotatedString)
            }
        }

        Column(modifier = Modifier
            .weight(0.4f)
            .drawBehind {
                drawCircle(
                    color = circleColor,
                    center = if (width <= 720 && height <= 1440) Offset(140f, 55f)
                    else if (width <= 1080 && height <= 2285) Offset(190f, 75f) else Offset(240f, 95f),
                    radius = if (width <= 720 && height <= 1440) 150f
                    else if (width <= 1080 && height <= 2285) 230f else 300f
                )
            }
            .padding(start = 35.dp, top = 16.dp)) {
            Surface(
                modifier = Modifier
                    .size(90.dp),
                shape = CircleShape,
                border = BorderStroke(1.5.dp, MaterialTheme.colorScheme.tertiary),
                color = Color.Transparent
            ) {
                Surface(
                    modifier = Modifier
                        .padding(5.dp),
                    shape = CircleShape,
                    color = MaterialTheme.colorScheme.background.copy(alpha = 0.5f)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_school),
                        contentDescription = "Profile Image",
                        modifier = Modifier.size(135.dp),
                        contentScale = ContentScale.Crop
                    )
                }
            }
        }
    }
}

