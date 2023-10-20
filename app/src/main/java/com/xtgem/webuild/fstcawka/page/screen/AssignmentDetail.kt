package com.xtgem.webuild.fstcawka.page.screen

import androidx.compose.runtime.Composable
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.lifecycle.viewmodel.compose.viewModel
import com.gandiva.neumorphic.neu
import com.gandiva.neumorphic.shape.RoundedCorner
import com.xtgem.webuild.fstcawka.models.entities.AssignmentContent
import com.xtgem.webuild.fstcawka.models.entities.AssignmentResult
import com.xtgem.webuild.fstcawka.models.entities.DataResult
import com.xtgem.webuild.fstcawka.models.relations.AssignmentContentAndResult
import com.xtgem.webuild.fstcawka.page.component.ErrorMessage
import com.xtgem.webuild.fstcawka.page.component.InvalidSessionFrame
import com.xtgem.webuild.fstcawka.page.component.LoadingData
import com.xtgem.webuild.fstcawka.page.viewmodel.AssignmentDetailViewModel
import com.xtgem.webuild.fstcawka.page.viewmodel.AssignmentDetailViewModelFactory
import com.xtgem.webuild.fstcawka.page.widget.CustomButton1
import com.xtgem.webuild.fstcawka.ui.theme.custom.MyFonts
import com.xtgem.webuild.fstcawka.ui.theme.custom.defaultFlatNeuAttrs
import com.xtgem.webuild.fstcawka.ui.theme.custom.defaultPressedNetAttrs

@Composable
fun AssignmentDetail(userId: String, sessionToken: String, assignmentId: String) {
    val viewModel: AssignmentDetailViewModel =
        viewModel(factory = AssignmentDetailViewModelFactory(userId, sessionToken, assignmentId))
    val assignment =
        remember { mutableStateOf(DataResult<AssignmentContentAndResult>(isLoading = true)) }
    LaunchedEffect(Unit) {
        viewModel.questions.observeForever { result ->
            assignment.value = result
        }
    }

    val currentQuestion = remember {
        mutableIntStateOf(0)
    }

    fun getFloat(): Float {
        val initQuestionSize = (assignment.value.data?.assignment?.size)
        val initAnswered =
            assignment.value.data?.result?.questionWithAnsweredIndex?.filter { it.value != null }?.size
        return if (initQuestionSize != null && initAnswered != null) (initAnswered / initQuestionSize.toDouble()).toFloat()
        else (0.2 / 2.0).toFloat()
    }

    val progress = remember {
        mutableFloatStateOf(0.01F)
    }

    val showRightAnswer = remember {
        mutableStateOf(false)
    }
    val clickIndex = remember {
        mutableStateOf<Int?>(null)
    }
    val showDone = remember {
        mutableStateOf(false)
    }
    val enableRadioButton = remember {
        mutableStateOf(true)
    }
    val tempStorage = remember {
        mutableStateOf(QuestionBlockModel())
    }

    if (showRightAnswer.value) {
        if (tempStorage.value.clickIndex != null) {
            viewModel.uploadResult(
                score = tempStorage.value.point!!,
                questionIndex = tempStorage.value.questionIndex!!,
                answeredIndex = tempStorage.value.clickIndex!!.toInt()
            )
        }
        progress.floatValue = getFloat()
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        when {
            assignment.value.isLoading -> {
                LoadingData()
            }

            assignment.value.sessionInvalid == false -> {
                InvalidSessionFrame()
            }

            else -> {
                val data = assignment.value.data
                if (data != null) {
                    Column(modifier = Modifier.padding(top = 35.dp, start = 16.dp, end = 16.dp)) {

                        // Question
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(0.8f)
                                .neu(defaultFlatNeuAttrs()),
                            shape = RoundedCornerShape(corner = CornerSize(6.dp)),
                            colors = CardDefaults.cardColors(
                                containerColor = MaterialTheme.colorScheme.background
                            ),
                            elevation = CardDefaults.cardElevation(8.dp)
                        ) {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .background(color = MaterialTheme.colorScheme.primary.copy(alpha = 0.7f))
                            ) {
                                // Tracker
                                Column(
                                    modifier = Modifier
                                        .weight(0.1f)
                                        .fillMaxWidth()
                                ) {
                                    Row(
                                        modifier = Modifier
                                            .wrapContentHeight()
                                            .padding(start = 8.dp, end = 8.dp, top = 18.dp)
                                    ) {
                                        // tracker text
                                        Column(
                                            modifier = Modifier.weight(0.8f),
                                            horizontalAlignment = Alignment.Start,
                                            verticalArrangement = Arrangement.Center
                                        ) {
                                            Text(
                                                text = "Question ${currentQuestion.intValue + 1} of ${assignment.value.data?.assignment?.size}",
                                                color = MaterialTheme.colorScheme.onBackground,
                                                style = TextStyle(
                                                    fontWeight = FontWeight.Light,
                                                    fontSize = 23.sp,
                                                    fontFamily = MyFonts.customFontFamily[0]
                                                )
                                            )

                                            Text(
                                                text = "Points: ${data.result.score}",
                                                color = MaterialTheme.colorScheme.onBackground.copy(
                                                    alpha = 0.4f
                                                ),
                                                style = TextStyle(
                                                    fontWeight = FontWeight.Light,
                                                    fontSize = 17.sp,
                                                    fontFamily = MyFonts.customFontFamily[5]
                                                ),
                                                modifier = Modifier.padding(top = 3.dp)
                                            )
                                        }

                                        // Progress Bar
                                        Column(
                                            modifier = Modifier.weight(0.2f),
                                            horizontalAlignment = Alignment.End,
                                            verticalArrangement = Arrangement.Center
                                        ) {
                                            Card(modifier = Modifier
                                                .widthIn()
                                                .heightIn()
                                                .neu(defaultPressedNetAttrs(cornerShape = RoundedCorner(50.dp)))
                                                .padding(5.dp),
                                                shape = RoundedCornerShape(corner = CornerSize(50.dp)),
                                                colors = CardDefaults.cardColors(
                                                    containerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.7f)
                                                )) {
                                                val progressFloat =
                                                    if (progress.floatValue == 0.01f) getFloat() else progress.floatValue
                                                showDone.value =
                                                    data.result.questionWithAnsweredIndex[currentQuestion.intValue] != null
                                                CircularProgressBar(
                                                    progress = progressFloat,
                                                    color = Color(0xFF000000),
                                                    showDone = showDone,
                                                    badgeColor = MaterialTheme.colorScheme.onBackground
                                                )
                                            }
                                        }
                                    }
                                }

                                // question and options
                                Column(
                                    modifier = Modifier
                                        .weight(0.9f)
                                        .padding(top = 15.dp)
                                ) {
                                    assignment.value.data?.assignment?.let {
                                        QuestionBlock(
                                            questionIndex = currentQuestion,
                                            //question = assignment.value.data!!.assignment[currentQuestion.intValue],
                                            question = data.assignment[currentQuestion.intValue],
                                            enableRadioButton, clickIndex = clickIndex,
                                            result = data.result,
                                            showRightAnswer = showRightAnswer
                                        ) {
                                            showRightAnswer.value = it.showRightAnswer ?: true
                                            clickIndex.value = it.clickIndex
                                            tempStorage.value = it
                                        }
                                    }
                                }
                            }
                        }


                        // Buttons
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(0.2f)
                                .padding(top = 16.dp)
                        ) {
                            val backgroundColor = Color(0xFF0097A7)
                            val textColor = Color(0xFFE4EAEB)
                            // previous button
                            Column(
                                modifier = Modifier
                                    .weight(1f)
                                    .padding(end = 10.dp)
                            ) {
                                CustomButton1(buttonText = "Previous", backgroundColor, textColor) {
                                    //handle click
                                    if (currentQuestion.intValue != 0) {
                                        currentQuestion.intValue = currentQuestion.intValue - 1
                                        enableRadioButton.value = true
                                        showRightAnswer.value = false
                                        clickIndex.value = null
                                    }
                                }
                            }

                            // next button
                            Column(
                                modifier = Modifier
                                    .weight(1f)
                                    .padding(start = 10.dp)
                            ) {
                                val nextButtonColor =
                                    if (isSystemInDarkTheme()) Color(0xFFFFFFFF) else Color(
                                        0xFF000000
                                    )
                                val nestTextColor =
                                    if (isSystemInDarkTheme()) Color(0xFF000000) else Color(
                                        0xFFE4EAEB
                                    )
                                CustomButton1(buttonText = "Next", nextButtonColor, nestTextColor) {
                                    // handle click
                                    if (currentQuestion.intValue != assignment.value.data!!.assignment.size - 1) {
                                        currentQuestion.intValue = currentQuestion.intValue + 1
                                        enableRadioButton.value = true
                                        showRightAnswer.value = false
                                        clickIndex.value = null
                                    }
                                }
                            }
                        }
                    }
                } else {
                    ErrorMessage(
                        delay = 2000L,
                        message = "Oops. Assignment Questions Not Available",
                        load = true
                    )
                }
            }
        }
    }
}

data class QuestionBlockModel(
    val showRightAnswer: Boolean? = null,
    val clickIndex: Int? = null,
    val point: Int? = null,
    val questionIndex: Int? = null,
    val answeredIndex: Int? = null

)

@Composable
fun QuestionBlock(
    questionIndex: State<Int>, question: AssignmentContent,
    enableRadioButton: MutableState<Boolean>,
    clickIndex: State<Int?>,
    showRightAnswer: MutableState<Boolean>,
    result: AssignmentResult,
    onQuestionClick: (QuestionBlockModel) -> Unit
) {

    // Question
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background
        ),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            // Tracker
            Column(
                modifier = Modifier
                    .weight(0.1f)
                    .fillMaxWidth()
                    .background(color = MaterialTheme.colorScheme.background)
            ) {

                // question and options
                Column(
                    modifier = Modifier
                        .weight(0.9f)
                        .padding(top = 16.dp)
                ) {
                    Text(
                        text = question.question,
                        color = MaterialTheme.colorScheme.onBackground,
                        style = TextStyle(
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 20.sp, fontFamily = MyFonts.customFontFamily[3]
                        ),
                        modifier = Modifier.padding(start = 16.dp)
                    )

                    // options
                    LazyColumn(modifier = Modifier.padding(top = 10.dp)) {
                        items(question.options) { option ->
                            if (option.trim().isNotBlank()) {
                                val isQuestionSolved =
                                    result.questionWithAnsweredIndex[questionIndex.value] != null
                                enableRadioButton.value = !isQuestionSolved
                                val history = result.questionWithAnsweredIndex[questionIndex.value]
                                QuestionOptions(
                                    options = option,
                                    optionsIndex = question.options.indexOf(option),
                                    answerIndex = question.answersIndex.trim().toInt(),
                                    enableRadioButton = enableRadioButton,
                                    showRightAnswer = showRightAnswer,
                                    historyIndex = history,
                                    clickIndex = clickIndex
                                ) {
                                    showRightAnswer.value = true
                                    val point = if (it.clickIndex == question.answersIndex.trim()
                                            .toInt()
                                    ) 1 else 0
                                    onQuestionClick(
                                        QuestionBlockModel(
                                            showRightAnswer = true,
                                            clickIndex = it.clickIndex,
                                            point = point,
                                            questionIndex = questionIndex.value,
                                            answeredIndex = question.answersIndex.trim().toInt()
                                        )
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun QuestionOptions(
    options: String, optionsIndex: Int, answerIndex: Int, enableRadioButton: State<Boolean>,
    showRightAnswer: State<Boolean>,
    historyIndex: Int?, clickIndex: State<Int?>, onClick: (QuestionBlockModel) -> Unit
) {

    fun checkRb(): Boolean {
        if (!enableRadioButton.value) {
            if (optionsIndex == historyIndex) {
                return true
            }
        } else {
            if (clickIndex.value == optionsIndex) {
                return true
            }
        }
        return false
    }

    fun backgroundColor(): Color {
        if (!enableRadioButton.value) {
            if (optionsIndex == historyIndex) {
                return if (historyIndex == answerIndex) {
                    Color(0xFF43A047).copy(alpha = 0.25f)
                } else {
                    Color(0xFFFF5252).copy(alpha = 0.25f)
                }
            } else {
                if (optionsIndex == answerIndex) {
                    return Color(0xFF43A047).copy(alpha = 0.25f)
                }
            }
        } else {
            if (clickIndex.value == optionsIndex) {
                return if (optionsIndex == answerIndex) {
                    Color(0xFF43A047).copy(alpha = 0.8f)
                } else {
                    Color(0xFFFF5252).copy(alpha = 0.8f)
                }
            } else {
                if (clickIndex.value != null) {
                    if (optionsIndex == answerIndex) {
                        return Color(0xFF43A047).copy(alpha = 0.8f)
                    }
                }
            }
        }
        return Color.Transparent
    }


    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(shape = RoundedCornerShape(corner = CornerSize(2.dp)))
                .background(backgroundColor())
                .padding(5.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            RadioButton(
                selected = checkRb(),
                modifier = Modifier.size(40.dp),
                onClick = { onClick(QuestionBlockModel(clickIndex = optionsIndex)) },
                enabled = enableRadioButton.value && !showRightAnswer.value
            )
            Text(
                text = options,
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f),
                style = TextStyle(
                    fontWeight = FontWeight.Light,
                    fontSize = 18.sp, fontFamily = MyFonts.customFontFamily[3]
                ),
                modifier = Modifier.padding(start = 5.dp)
            )

        }
    }
}


@Composable
fun CircularProgressBar(
    progress: Float,
    color: Color = Color.Blue,
    badgeColor: Color,
    showDone: State<Boolean>
) {
    val startAngle = 270f
    val sweepAngle = progress * 360
    Box {

        Canvas(
            modifier = Modifier
                .size(35.dp),
            onDraw = {
                drawArc(
                    color = color,
                    style = Stroke(width = 3.5F),
                    startAngle = startAngle,
                    sweepAngle = sweepAngle,
                    useCenter = false,
                    topLeft = Offset(0f, 0f),
                    size = size.copy(height = size.width)
                )
            }
        )

        if (showDone.value) {
            Canvas(
                modifier = Modifier
                    .size(10.dp),
                onDraw = {
                    drawArc(
                        color = badgeColor,
                        style = Stroke(width = 20f),
                        startAngle = startAngle,
                        sweepAngle = 1f * 360,
                        useCenter = false,
                        topLeft = Offset(22f, 21f),
                        size = size.copy(height = size.width)
                    )
                }
            )
        }

    }
}