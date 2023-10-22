package com.xtgem.webuild.fstcawka.page.screen

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.gandiva.neumorphic.neu
import com.xtgem.webuild.fstcawka.misc.Uncategorized
import com.xtgem.webuild.fstcawka.models.entities.Assignment
import com.xtgem.webuild.fstcawka.models.entities.AssignmentResult
import com.xtgem.webuild.fstcawka.models.entities.DataResult
import com.xtgem.webuild.fstcawka.models.enums.Screens
import com.xtgem.webuild.fstcawka.models.relations.AssignmentWithResults
import com.xtgem.webuild.fstcawka.page.component.ErrorMessage
import com.xtgem.webuild.fstcawka.page.component.InvalidSessionFrame
import com.xtgem.webuild.fstcawka.page.component.LoadingData
import com.xtgem.webuild.fstcawka.page.viewmodel.AssignmentListScreenViewModel
import com.xtgem.webuild.fstcawka.page.viewmodel.AssignmentListScreenViewModelFactory
import com.xtgem.webuild.fstcawka.ui.theme.custom.MyFonts
import com.xtgem.webuild.fstcawka.ui.theme.custom.defaultFlatNeuAttrs
import com.xtgem.webuild.fstcawka.ui.theme.custom.defaultPressedNetAttrs

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AssignmentListScreen(userId: String, sessionToken: String, navController: NavController) {
    val viewModel: AssignmentListScreenViewModel = viewModel(factory = AssignmentListScreenViewModelFactory(userId, sessionToken))
    val assignments = remember { mutableStateOf(DataResult<List<AssignmentWithResults>>(isLoading = true)) }
    LaunchedEffect(Unit) {
        viewModel.assignments.observeForever { result ->
            assignments.value = result
        }
    }
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            Header(assignments = assignments.value)
        }
    ) {
        it.calculateBottomPadding()
        Surface(modifier = Modifier
            .fillMaxSize()
            .padding(it),
            color = MaterialTheme.colorScheme.background
        ) {
            Column(modifier = Modifier.height(16.dp)) {}

            when {
                assignments.value.isLoading -> {
                    LoadingData()
                }
                assignments.value.sessionInvalid == false -> {
                    InvalidSessionFrame()
                }
                else -> {
                    val data = assignments.value.data
                    if (data != null) {
                        AssignmentList(
                            assignments = data
                        ) { assignment ->
                            navController.navigate(Screens.AssignmentDetail.withArg(userId,
                                sessionToken,
                                assignment.assignmentId.toString()))
                        }
                    }else {
                        ErrorMessage(
                            delay = 2000L,
                            message = "Oops. Assignment Not Available",
                            load = true
                        )
                    }
                }
            }
        }
    }
}


@Composable
fun Header(assignments: DataResult<List<AssignmentWithResults>>) {
    val caption = remember {
        mutableStateOf("")
    }
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.secondary
        ),
        elevation = CardDefaults.cardElevation(6.dp)
    ) {
        when  {
            assignments.isLoading  -> { caption.value = "Please wait"  }
            else -> {
                val data = assignments.data
                if (data != null) {
                    val finishedSize = data.map { i ->
                        val answeredNumber = i.results.questionSize - i.results.questionWithAnsweredIndex.values.filterNotNull().size
                        answeredNumber.takeIf { it == 0 }
                    }
                    caption.value = if (finishedSize.isEmpty())"You have no finished assignment"
                    else "You have ${finishedSize.size} finished assignment"
                }
            }
        }

        Column(modifier = Modifier
            .background(color = MaterialTheme.colorScheme.secondary)
            .fillMaxWidth()
            .padding(top = 10.dp, start = 16.dp)
        ) {
            Text(text = if (assignments.isLoading) "Loading Assignments" else "Assignments",
                color = MaterialTheme.colorScheme.onBackground,
                style = TextStyle(fontWeight = FontWeight.Bold,
                    fontSize = 25.sp, fontFamily = MyFonts.customFontFamily[0])
            )
            Text(text = caption.value,
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f),
                style = TextStyle(fontWeight = FontWeight.Light,
                    fontSize = 16.sp, fontFamily = MyFonts.customFontFamily[2]),
                modifier = Modifier.padding(top = 0.dp)
            )
        }
    }
}

@Composable
fun AssignmentList(
    assignments: List<AssignmentWithResults>,
    onAssignmentClick: (Assignment) -> Unit
) {
    LazyColumn(modifier = Modifier.padding(start = 5.dp, end = 5.dp)) {
        items(assignments) {
            AssignmentCell(
                assignment = it.assignment, assignmentResult = it.results) { clickAssignment ->
                onAssignmentClick(clickAssignment)
            }
        }
        item {
            Column(modifier = Modifier.height(16.dp)) {}
        }
    }
}

@Composable
fun AssignmentCell(
    assignment: Assignment,
    assignmentResult: AssignmentResult,
    onClick: (Assignment) -> Unit
) {
    Card(modifier = Modifier
        .fillMaxWidth()
        .height(145.dp)
        .neu(defaultFlatNeuAttrs())
        .padding(top = 10.dp, bottom = 0.dp, start = 8.dp, end = 8.dp)
        .clickable { onClick(assignment) },
        shape = RoundedCornerShape(corner = CornerSize(4.dp)),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background
        ),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)) {

            Row(modifier = Modifier
                .weight(0.9f)
                .fillMaxWidth()) {
                // Subject
                Column(modifier = Modifier
                    .weight(0.8f)
                    .padding(top = 16.dp),
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(text = assignment.subject.subjectName,
                        color = MaterialTheme.colorScheme.onBackground,
                        style = TextStyle(fontWeight = FontWeight.Light,
                            fontSize = 25.sp, fontFamily = MyFonts.customFontFamily[2]),
                        modifier = Modifier.padding(top = 3.dp))
                    Text(text = "${assignment.description.take(100)}...",
                        color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f),
                        style = TextStyle(fontWeight = FontWeight.Light,
                            fontSize = 15.sp, fontFamily = MyFonts.customFontFamily[5]),
                        modifier = Modifier.padding(top = 3.dp))
                }

                Column(modifier = Modifier
                    .weight(0.2f),
                    horizontalAlignment = Alignment.End
                ) {
                    Text(text = "Due Date:",
                        color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f),
                        style = TextStyle(fontWeight = FontWeight.Light,
                            fontSize = 18.sp, fontFamily = MyFonts.customFontFamily[2]),
                        modifier = Modifier.padding(top = 3.dp))

                    Text(text = Uncategorized().simpleDateFormatter(assignment.dueDateTime),
                        color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f),
                        style = TextStyle(fontWeight = FontWeight.Light,
                            fontSize = 13.sp, fontFamily = MyFonts.customFontFamily[2]),
                        modifier = Modifier
                            .padding(top = 3.dp, start = 9.dp)
                            .align(alignment = Alignment.Start))
                }
            }

            Column(modifier = Modifier
                .weight(0.1f)
                .fillMaxWidth(),
                verticalArrangement = Arrangement.Bottom
            ) {
                Card(modifier = Modifier
                    .fillMaxWidth()
                    .height(135.dp)
                    .neu(defaultPressedNetAttrs())
                    .padding(5.dp),
                    shape = RoundedCornerShape(corner = CornerSize(4.dp)),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.background
                    )) {
                    Column(modifier = Modifier
                        .fillMaxWidth()
                        .clip(shape = RoundedCornerShape(corner = CornerSize(6.dp)))
                        .border(
                            width = 1.dp,
                            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.0f)
                        )
                        .height(5.dp)) {

                        val diff = assignmentResult.questionWithAnsweredIndex.values.filterNotNull().size / assignmentResult.questionSize.toDouble()
                        Divider(modifier = Modifier
                            .fillMaxWidth(diff.toFloat())
                            .fillMaxHeight(),
                            color = MaterialTheme.colorScheme.primary
                        )
                    }
                }
            }

        }
    }
}
