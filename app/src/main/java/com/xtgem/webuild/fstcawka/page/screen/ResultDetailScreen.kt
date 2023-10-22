package com.xtgem.webuild.fstcawka.page.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.gandiva.neumorphic.neu
import com.xtgem.webuild.fstcawka.models.entities.DataResult
import com.xtgem.webuild.fstcawka.models.entities.Student
import com.xtgem.webuild.fstcawka.models.entities.StudentResult
import com.xtgem.webuild.fstcawka.page.component.ErrorMessage
import com.xtgem.webuild.fstcawka.page.component.InvalidSessionFrame
import com.xtgem.webuild.fstcawka.page.viewmodel.ResultDetailScreenViewModel
import com.xtgem.webuild.fstcawka.page.viewmodel.ResultDetailScreenViewModelFactory
import com.xtgem.webuild.fstcawka.ui.theme.custom.MyFonts
import com.xtgem.webuild.fstcawka.ui.theme.custom.defaultPressedNetAttrs

@Composable
fun ResultDetailScreen(userId: String, sessionToken: String, resultId: String) {
    val viewModel: ResultDetailScreenViewModel = viewModel(factory = ResultDetailScreenViewModelFactory(userId, sessionToken, resultId))
    val result = remember { mutableStateOf(DataResult<StudentResult>(isLoading = true)) }
    val student = remember { mutableStateOf(DataResult<Student>(isLoading = true)) }
    LaunchedEffect(Unit) {
        viewModel.result.observeForever { result.value = it }
        viewModel.student.observeForever { student.value = it }
    }

    Surface(modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background) {
        when {
            result.value.isLoading -> {
                LoginScreen()
            }
            result.value.sessionInvalid == false -> {
                InvalidSessionFrame()
            }
            else -> {
                val resultData = result.value.data
                val studentData = student.value.data
                if (resultData != null && studentData != null) {
                    Column(modifier = Modifier
                        .wrapContentHeight()
                        .padding(top = 40.dp, start = 16.dp, end = 16.dp)) {
                        ResultDetailCell(results = resultData, student = studentData)
                    }
                }else {
                    ErrorMessage(
                        delay = 2000L,
                        message = "Oops. Result Not Available",
                        load = true
                    )
                }
            }
        }
    }

}

@Composable
fun ResultDetailCell(results: StudentResult,
                   student: Student) {
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
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 16.dp, end = 16.dp, top = 16.dp)
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.1f)
                    .neu(defaultPressedNetAttrs()),
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
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = student.firstName + " " + student.lastName, fontSize = 25.sp,
                        color = MaterialTheme.colorScheme.primary,
                        style = MaterialTheme.typography.headlineLarge
                    )
                }
            }

            Column(
                modifier = Modifier
                    .weight(0.9f)
                    .padding(top = 16.dp)
            ) {

                // Subject Name
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(45.dp)
                        .padding(top = 16.dp)
                ) {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = results.subject.subjectName,
                            color = MaterialTheme.colorScheme.onBackground,
                            style = TextStyle(
                                fontWeight = FontWeight.Bold,
                                fontSize = 23.sp, fontFamily = MyFonts.customFontFamily[2]
                            )
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // MidTerm
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(45.dp)
                        .padding(top = 4.dp, bottom = 4.dp)
                ) {
                    Column(
                        modifier = Modifier.weight(0.5f),
                        horizontalAlignment = Alignment.Start
                    ) {
                        Text(
                            text = "MidTerm Test",
                            color = MaterialTheme.colorScheme.onBackground,
                            style = TextStyle(
                                fontWeight = FontWeight.Bold,
                                fontSize = 18.sp, fontFamily = MyFonts.customFontFamily[3]
                            )
                        )
                    }
                    Column(
                        modifier = Modifier
                            .weight(0.5f)
                            .fillMaxHeight(),
                        horizontalAlignment = Alignment.End
                    ) {
                        Text(
                            text = results.midTermScore.toString(),
                            color = MaterialTheme.colorScheme.onBackground,
                            style = TextStyle(
                                fontWeight = FontWeight.Bold,
                                fontSize = 18.sp, fontFamily = MyFonts.customFontFamily[3]
                            )
                        )
                    }
                }

                // WelcomeBack
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(45.dp)
                        .padding(top = 4.dp, bottom = 4.dp)
                ) {
                    Column(
                        modifier = Modifier.weight(0.5f),
                        horizontalAlignment = Alignment.Start
                    ) {
                        Text(
                            text = "WelcomeBack Test",
                            color = MaterialTheme.colorScheme.onBackground,
                            style = TextStyle(
                                fontWeight = FontWeight.Bold,
                                fontSize = 18.sp, fontFamily = MyFonts.customFontFamily[3]
                            )
                        )
                    }
                    Column(
                        modifier = Modifier
                            .weight(0.5f)
                            .fillMaxHeight(),
                        horizontalAlignment = Alignment.End
                    ) {
                        Text(
                            text = results.welcomeBackTest.toString(),
                            color = MaterialTheme.colorScheme.onBackground,
                            style = TextStyle(
                                fontWeight = FontWeight.Bold,
                                fontSize = 18.sp, fontFamily = MyFonts.customFontFamily[3]
                            )
                        )
                    }
                }

                // Assignment
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(45.dp)
                        .padding(top = 4.dp, bottom = 4.dp)
                ) {
                    Column(
                        modifier = Modifier.weight(0.5f),
                        horizontalAlignment = Alignment.Start
                    ) {
                        Text(
                            text = "Assignment",
                            color = MaterialTheme.colorScheme.onBackground,
                            style = TextStyle(
                                fontWeight = FontWeight.Bold,
                                fontSize = 18.sp, fontFamily = MyFonts.customFontFamily[3]
                            )
                        )
                    }
                    Column(
                        modifier = Modifier
                            .weight(0.5f)
                            .fillMaxHeight(),
                        horizontalAlignment = Alignment.End
                    ) {
                        Text(
                            text = results.assignmentsScore.toString(),
                            color = MaterialTheme.colorScheme.onBackground,
                            style = TextStyle(
                                fontWeight = FontWeight.Bold,
                                fontSize = 18.sp, fontFamily = MyFonts.customFontFamily[3]
                            )
                        )
                    }
                }

                // Examination
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(45.dp)
                        .padding(top = 4.dp, bottom = 4.dp)
                ) {
                    Column(
                        modifier = Modifier.weight(0.5f),
                        horizontalAlignment = Alignment.Start
                    ) {
                        Text(
                            text = "Examination",
                            color = MaterialTheme.colorScheme.onBackground,
                            style = TextStyle(
                                fontWeight = FontWeight.Bold,
                                fontSize = 18.sp, fontFamily = MyFonts.customFontFamily[3]
                            )
                        )
                    }
                    Column(
                        modifier = Modifier
                            .weight(0.5f)
                            .fillMaxHeight(),
                        horizontalAlignment = Alignment.End
                    ) {
                        Text(
                            text = results.examScore.toString(),
                            color = MaterialTheme.colorScheme.onBackground,
                            style = TextStyle(
                                fontWeight = FontWeight.Bold,
                                fontSize = 18.sp, fontFamily = MyFonts.customFontFamily[3]
                            )
                        )
                    }
                }

                // Total Score
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(45.dp)
                        .padding(top = 4.dp, bottom = 4.dp)
                ) {
                    Column(
                        modifier = Modifier.weight(0.5f),
                        horizontalAlignment = Alignment.Start
                    ) {
                        Text(
                            text = "Total Score",
                            color = MaterialTheme.colorScheme.onBackground,
                            style = TextStyle(
                                fontWeight = FontWeight.Bold,
                                fontSize = 18.sp, fontFamily = MyFonts.customFontFamily[3]
                            )
                        )
                    }
                    Column(
                        modifier = Modifier
                            .weight(0.5f)
                            .fillMaxHeight(),
                        horizontalAlignment = Alignment.End
                    ) {
                        Text(
                            text = results.totalScore.toString() + "%",
                            color = MaterialTheme.colorScheme.onBackground,
                            style = TextStyle(
                                fontWeight = FontWeight.Bold,
                                fontSize = 18.sp, fontFamily = MyFonts.customFontFamily[3]
                            )
                        )
                    }
                }

                // Grade
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(45.dp)
                        .padding(top = 4.dp, bottom = 4.dp)
                ) {
                    Column(
                        modifier = Modifier.weight(0.5f),
                        horizontalAlignment = Alignment.Start
                    ) {
                        Text(
                            text = "Grade",
                            color = MaterialTheme.colorScheme.onBackground,
                            style = TextStyle(
                                fontWeight = FontWeight.Bold,
                                fontSize = 18.sp, fontFamily = MyFonts.customFontFamily[3]
                            )
                        )
                    }
                    Column(
                        modifier = Modifier
                            .weight(0.5f)
                            .fillMaxHeight(),
                        horizontalAlignment = Alignment.End
                    ) {
                        val grade = if((results.totalScore ?: 60) < 50) {
                            "D - Poor"
                        }
                        else if((results.totalScore ?: 60) < 60) {
                            "P - Pass"
                        }
                        else if((results.totalScore ?: 60) < 70) {
                            "C - Credit"
                        }
                        else if((results.totalScore ?: 60) < 85) {
                            "B - Good"
                        }
                        else {
                            "A - Excellent"
                        }
                        Text(
                            text = grade,
                            color = MaterialTheme.colorScheme.onBackground,
                            style = TextStyle(
                                fontWeight = FontWeight.Bold,
                                fontSize = 18.sp, fontFamily = MyFonts.customFontFamily[3]
                            )
                        )
                    }
                }
            }
        }
    }
}