package com.xtgem.webuild.fstcawka.page.screen

import android.app.DatePickerDialog
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Logout
import androidx.compose.material.icons.sharp.AccountCircle
import androidx.compose.material.icons.twotone.AccountCircle
import androidx.compose.material.icons.twotone.CalendarMonth
import androidx.compose.material.icons.twotone.Email
import androidx.compose.material.icons.twotone.Image
import androidx.compose.material.icons.twotone.Password
import androidx.compose.material.icons.twotone.Smartphone
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.gandiva.neumorphic.neu
import com.xtgem.webuild.fstcawka.misc.Uncategorized
import com.xtgem.webuild.fstcawka.models.entities.DataResult
import com.xtgem.webuild.fstcawka.models.entities.Student
import com.xtgem.webuild.fstcawka.page.component.ErrorMessage
import com.xtgem.webuild.fstcawka.page.component.ErrorPage
import com.xtgem.webuild.fstcawka.page.component.InvalidSessionFrame
import com.xtgem.webuild.fstcawka.page.component.LoadingData
import com.xtgem.webuild.fstcawka.page.viewmodel.ProfileScreenViewModel
import com.xtgem.webuild.fstcawka.page.viewmodel.ProfileScreenViewModelFactory
import com.xtgem.webuild.fstcawka.page.widget.CustomButton1
import com.xtgem.webuild.fstcawka.page.widget.InputText
import com.xtgem.webuild.fstcawka.page.widget.PasswordInputField
import com.xtgem.webuild.fstcawka.ui.theme.custom.defaultPressedNetAttrs
import java.time.LocalDateTime

@Composable
fun ProfileScreen(userId: String, sessionToken: String, navController: NavController = rememberNavController()) {
    val viewModel: ProfileScreenViewModel = remember(userId, sessionToken) {
        ProfileScreenViewModelFactory(userId, sessionToken)
    }.create(ProfileScreenViewModel::class.java)

    val getStudent = remember { mutableStateOf(DataResult<Student>(isLoading = true)) }
    LaunchedEffect(Unit) {
        viewModel.student.observeForever { data ->
            getStudent.value = data
        }
    }

    val student = remember {
        mutableStateOf<Student?>(null)
    }

    val context = LocalContext.current
    val displayMetrics = context.resources.displayMetrics
    val width = displayMetrics.widthPixels
    val height = displayMetrics.heightPixels

    val updateFullName = remember {
        mutableStateOf(false)
    }
    if (updateFullName.value) {
        student.value?.let {
            UpdateProfileDialog(student = it) { itData ->
                student.value = itData
                updateFullName.value = false
            }
        }
    }

    val updateMobile = remember {
        mutableStateOf(false)
    }
    if (updateMobile.value) {
        student.value?.let {
            UpdateMobileDialog(student = it, context = context) { itData ->
                student.value = itData
                updateMobile.value = false
            }
        }
    }

    val updateEmail = remember {
        mutableStateOf(false)
    }
    if (updateEmail.value) {
        student.value?.let {
            UpdateEmailDialog(student = it, context = context) { itData ->
                student.value = itData
                updateEmail.value = false
            }
        }
    }

    val updateImage = remember {
        mutableStateOf(false)
    }
    if (updateImage.value) {
        student.value?.let {
            UpdateImageDialog(student = it, context = context) { itData ->
                student.value = itData
                updateImage.value = false
            }
        }
    }

    val updatePassword = remember {
        mutableStateOf(false)
    }
    if (updatePassword.value) {
        student.value?.let {
            UpdatePasswordDialog(student = it, context = context) { itData ->
                student.value = itData
                updatePassword.value = false
            }
        }
    }

    val updateDate = remember {
        mutableStateOf(false)
    }
    if (updateDate.value) {
        UpdateDateDialog(student = student, context = context, updateDate)
    }

    val viewFullImage = remember {
        mutableStateOf(false)
    }
    if (viewFullImage.value) {
        ViewProfileImageDialog(imageLink = student.value!!.imageLink) {
            viewFullImage.value = false
        }
    }



    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .weight(0.3f)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    val circleColor = MaterialTheme.colorScheme.primary
                    Column(modifier = Modifier
                        .fillMaxSize()
                        .drawBehind {
                            // width: 720 => height: 1440
                            //width: 1080 => height: 2285
                            drawCircle(
                                color = circleColor,
                                center = if (width <= 720 && height <= 1440) Offset(350f, -500f)
                                else if (width <= 1080 && height <= 2285) Offset(
                                    550f,
                                    -500f
                                ) else Offset(750f, -500f),
                                radius = if (width <= 720 && height <= 1440) 800f
                                else if (width <= 1080 && height <= 2285) 1000f else 1200f
                            )
                        },
                        verticalArrangement = Arrangement.Top,
                        horizontalAlignment = Alignment.End) {
                        Column(modifier = Modifier
                            .size(80.dp)
                            .padding(10.dp),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally) {
                            Column(modifier = Modifier
                                .size(80.dp)
                                .padding(5.dp),
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally) {
                                Icon(imageVector = Icons.Outlined.Logout, contentDescription = null,
                                    tint = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.0f),
                                    modifier = Modifier.size(30.dp))
                            }
                        }
                    }
                    Column(
                        modifier = Modifier
                            .fillMaxSize(),
                        verticalArrangement = Arrangement.Bottom,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Surface(
                            modifier = Modifier
                                .size(200.dp)
                                .clip(CircleShape)
                                .background(MaterialTheme.colorScheme.background),
                        ) {
                            when {
                                getStudent.value.isLoading -> {
                                    LoadingData()
                                }

                                getStudent.value.error != null -> {
                                    ErrorPage("News")
                                }

                                getStudent.value.sessionInvalid == false -> {
                                    InvalidSessionFrame()
                                }

                                else -> {
                                    val data = getStudent.value.data
                                    if (data != null) {
                                        if (student.value == null) data.let { student.value = it }
                                        Column(
                                            modifier = Modifier
                                                .fillMaxSize()
                                                .padding(8.dp)
                                        ) {
                                            AsyncImage(
                                                model = getStudent.value.data?.imageLink,
                                                contentDescription = null,
                                                contentScale = ContentScale.Crop,
                                                modifier = Modifier
                                                    .fillMaxSize()
                                                    .clip(CircleShape)
                                                    .clickable { viewFullImage.value = true }
                                            )
                                        }
                                    } else {
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

                Column(
                    modifier = Modifier
                        .weight(0.7f)
                        .padding(top = 30.dp)
                ) {

                    when {
                        getStudent.value.isLoading -> {
                            LoadingData()
                        }

                        getStudent.value.error != null -> {
                            ErrorPage("News")
                        }

                        getStudent.value.sessionInvalid == false -> {
                            InvalidSessionFrame()
                        }

                        else -> {
                            val data = getStudent.value.data
                            if (data != null) {
                               if (student.value == null) data.let { student.value = it }
                                // name
                                val accountIcon = Icons.TwoTone.AccountCircle
                                ProfileCell(
                                    icon = accountIcon,
                                    label = "${student.value!!.firstName} ${student.value!!.lastName}"
                                ) {
                                    updateFullName.value = true
                                }

                                // birth date
                                val dobIcon = Icons.TwoTone.CalendarMonth
                                ProfileCell(
                                    icon = dobIcon,
                                    label = Uncategorized().simpleDateFormatter(student.value!!.dateOfBirth)
                                ) {
                                    updateDate.value = true
                                }


                                // mobile
                                val mobileIcon = Icons.TwoTone.Smartphone
                                ProfileCell(
                                    icon = mobileIcon,
                                    label = student.value!!.guardianMobileNo
                                ) {
                                    updateMobile.value = true
                                }


                                // email
                                val emailIcon = Icons.TwoTone.Email
                                ProfileCell(
                                    icon = emailIcon,
                                    label = student.value!!.guardianEmail
                                ) {
                                    updateEmail.value = true
                                }


                                // image
                                val imageIcon = Icons.TwoTone.Image
                                ProfileCell(
                                    icon = imageIcon,
                                    label = "${student.value!!.imageLink.take(15)}..."
                                ) {
                                    updateImage.value = true
                                }


                                // password
                                val passwordIcon = Icons.TwoTone.Password
                                ProfileCell(
                                    icon = passwordIcon,
                                    label = "********"
                                ) {
                                    updatePassword.value = true
                                }

                                Column(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(100.dp)
                                        .padding(start = 16.dp, end = 16.dp, top = 25.dp)
                                ) {
                                    val buttonModifier = Modifier.height(55.dp)
                                    CustomButton1(
                                        buttonText = "Update Profile",
                                        modifier = buttonModifier
                                    ) {
                                        // update the database and go back
                                        student.value?.let { viewModel.insertStudent(it) }
                                        navController.popBackStack()
                                    }
                                }
                            } else {
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
fun ViewProfileImageDialog(imageLink: String, onDismiss: () -> Unit) {
    Dialog(onDismissRequest = { onDismiss() }) {
        AsyncImage(model = imageLink, contentDescription = null, modifier = Modifier.size(500.dp))
    }
}

@Composable
fun ProfileCell(icon: ImageVector = Icons.Sharp.AccountCircle,
                label: String = "Elizabeth Johnson", onClick: () -> Unit) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .height(70.dp)
        .padding(start = 16.dp, end = 16.dp, bottom = 8.dp, top = 8.dp)
        .clickable {
            onClick()
        }) {
        Column(modifier = Modifier
            .weight(0.3f)
            .fillMaxHeight(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally) {
            Icon(imageVector = icon, contentDescription = null,
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(45.dp))
        }

        Column(modifier = Modifier
            .weight(0.7f)
            .fillMaxHeight(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start) {
            Text(text = label, fontSize = 18.sp,
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.8f),
                style = MaterialTheme.typography.headlineSmall)
        }
    }
    Divider(modifier = Modifier
        .fillMaxWidth()
        .height(0.3.dp),
        color = MaterialTheme.colorScheme.onBackground)
}



@Composable
fun UpdateProfileDialog(student: Student,
                        onDismiss: (Student) -> Unit) {
    val firstName = remember {
        mutableStateOf("")
    }
    val lastName = remember {
        mutableStateOf("")
    }

    val interactFirstName = remember {
        MutableInteractionSource()
    }
    val interactLastName = remember {
        MutableInteractionSource()
    }

    Dialog(onDismissRequest = { onDismiss(student) }) {
        Surface(modifier = Modifier
            .fillMaxWidth()
            .heightIn()
            .neu(defaultPressedNetAttrs())
            .background(MaterialTheme.colorScheme.background.copy(alpha = 0.0f)),
            shape = RoundedCornerShape(12.dp)) {
                Column(modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)) {
                    val inputFieldsModifier = Modifier
                        .fillMaxHeight()
                        .fillMaxWidth()
                        .background(
                            shape = RoundedCornerShape(corner = CornerSize(6.dp)),
                            color = Color.Transparent
                        )

                    Spacer(modifier = Modifier.height(10.dp))

                    Column(modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(text = "Update Student Name", fontSize = 18.sp,
                            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.8f),
                            style = MaterialTheme.typography.headlineSmall)
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    Column(modifier = Modifier
                        .fillMaxWidth()
                        .height(55.dp)) {
                        val keyboardType = KeyboardType.Text
                        InputText(text = firstName.value,
                            label = if (firstName.value.isEmpty()) "First Name" else "",
                            keyboardType = keyboardType,
                            modifier = inputFieldsModifier,
                            interactionSource = interactFirstName,
                            isFieldBlank = false,
                            onTextChange = {
                                if (it.all { char ->
                                        char.isLetter() || char.isDigit()
                                    }) firstName.value = it

                            })
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    Column(modifier = Modifier
                        .fillMaxWidth()
                        .height(55.dp)
                        .background(
                            shape = RoundedCornerShape(corner = CornerSize(6.dp)),
                            color = Color(0xFFEEEFF1)
                        )) {
                        val keyboardType = KeyboardType.Text
                        InputText(text = lastName.value,
                            label = if (lastName.value.isEmpty()) "Last Name" else "",
                            keyboardType = keyboardType,
                            modifier = inputFieldsModifier,
                            interactionSource = interactLastName,
                            isFieldBlank = false,
                            onTextChange = {
                                if (it.all { char ->
                                        char.isLetter() || char.isWhitespace()
                                    }) lastName.value = it

                            })
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    Column(modifier = Modifier
                        .fillMaxWidth()
                        .height(80.dp)) {
                        val buttonModifier = Modifier.height(60.dp)
                        CustomButton1(buttonText = "Done",
                            modifier = buttonModifier) {
                            var newStudent = student
                            if (firstName.value.isNotBlank() && lastName.value.isNotBlank()) {
                                newStudent = student.copy(firstName = firstName.value,
                                    lastName = lastName.value)
                            }
                            if (firstName.value.isNotBlank()) {
                                newStudent = student.copy(firstName = firstName.value)
                            }
                            if (lastName.value.isNotBlank()) {
                                newStudent = student.copy(lastName = lastName.value)
                            }
                            onDismiss(newStudent)
                        }
                    }
                }
        }
    }
}

@Composable
fun UpdateMobileDialog(student: Student, context: Context,
                        onDismiss: (Student) -> Unit) {
    val mobile = remember {
        mutableStateOf("")
    }
    val interactMobile = remember {
        MutableInteractionSource()
    }

    val error = remember {
        mutableStateOf(false)
    }

    val toast = Toast.makeText(context, "Invalid Mobile", Toast.LENGTH_SHORT)

    Dialog(onDismissRequest = { onDismiss(student) }) {
        Surface(modifier = Modifier
            .fillMaxWidth()
            .heightIn()
            .neu(defaultPressedNetAttrs())
            .background(MaterialTheme.colorScheme.background.copy(alpha = 0.0f)),
            shape = RoundedCornerShape(12.dp)) {
            Column(modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)) {
                val inputFieldsModifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth()
                    .background(
                        shape = RoundedCornerShape(corner = CornerSize(6.dp)),
                        color = Color.Transparent
                    )

                Spacer(modifier = Modifier.height(10.dp))

                Column(modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = "Update Mobile", fontSize = 18.sp,
                        color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.8f),
                        style = MaterialTheme.typography.headlineSmall)
                }

                Spacer(modifier = Modifier.height(10.dp))

                Column(modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp)) {
                    val keyboardType = KeyboardType.Number
                    InputText(text = mobile.value,
                        label = if (mobile.value.isEmpty()) "Mobile" else "",
                        keyboardType = keyboardType,
                        modifier = inputFieldsModifier,
                        interactionSource = interactMobile,
                        isFieldBlank = error.value,
                        onTextChange = {
                            if (it.all { char ->
                                    char.isDigit()
                                }) mobile.value = it; error.value = false
                        })
                }

                Spacer(modifier = Modifier.height(10.dp))


                Spacer(modifier = Modifier.height(20.dp))

                Column(modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp)) {
                    val buttonModifier = Modifier.height(60.dp)
                    CustomButton1(buttonText = "Done",
                        modifier = buttonModifier) {
                        if (mobile.value.length != 11) {
                            error.value = true
                            toast?.cancel()
                            toast.show()
                        }
                        val newStudent: Student
                        if (mobile.value.isNotBlank() && mobile.value.length == 11) {
                            newStudent = student.copy(guardianMobileNo = mobile.value)
                            onDismiss(newStudent)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun UpdateEmailDialog(student: Student, context: Context,
                       onDismiss: (Student) -> Unit) {
    val email = remember {
        mutableStateOf("")
    }
    val interactEmail = remember {
        MutableInteractionSource()
    }

    val error = remember {
        mutableStateOf(false)
    }

    val toast = Toast.makeText(context, "Invalid Email", Toast.LENGTH_SHORT)

    Dialog(onDismissRequest = { onDismiss(student) }) {
        Surface(modifier = Modifier
            .fillMaxWidth()
            .heightIn()
            .neu(defaultPressedNetAttrs())
            .background(MaterialTheme.colorScheme.background.copy(alpha = 0.0f)),
            shape = RoundedCornerShape(12.dp)) {
            Column(modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)) {
                val inputFieldsModifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth()
                    .background(
                        shape = RoundedCornerShape(corner = CornerSize(6.dp)),
                        color = Color.Transparent
                    )

                Spacer(modifier = Modifier.height(10.dp))

                Column(modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = "Update Email", fontSize = 18.sp,
                        color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.8f),
                        style = MaterialTheme.typography.headlineSmall)
                }

                Spacer(modifier = Modifier.height(10.dp))

                Column(modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp)) {
                    val keyboardType = KeyboardType.Text
                    InputText(text = email.value,
                        label = if (email.value.isEmpty()) "Email" else "",
                        keyboardType = keyboardType,
                        modifier = inputFieldsModifier,
                        interactionSource = interactEmail,
                        isFieldBlank = error.value,
                        onTextChange = {
                            email.value = it; error.value = false
                        })
                }

                Spacer(modifier = Modifier.height(10.dp))


                Spacer(modifier = Modifier.height(20.dp))

                Column(modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp)) {
                    val buttonModifier = Modifier.height(60.dp)
                    CustomButton1(buttonText = "Done",
                        modifier = buttonModifier) {
                        val newStudent: Student
                        if (!email.value.matches(Regex("[a-zA-Z\\d._%+-]+@[a-zA-Z\\d.-]+\\.[a-zA-Z]{2,}"))) {
                            error.value = true
                            toast?.cancel()
                            toast.show()
                        }

                        if (email.value.isNotBlank()
                            && email.value.matches(Regex("[a-zA-Z\\d._%+-]+@[a-zA-Z\\d.-]+\\.[a-zA-Z]{2,}"))) {
                            newStudent = student.copy(guardianEmail = email.value)
                            onDismiss(newStudent)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun UpdateImageDialog(student: Student, context: Context,
                      onDismiss: (Student) -> Unit) {
    val imageLink = remember {
        mutableStateOf("")
    }
    val interactImageLink = remember {
        MutableInteractionSource()
    }

    val error = remember {
        mutableStateOf(false)
    }

    val toast = Toast.makeText(context, "Invalid Image URL", Toast.LENGTH_SHORT)
    Log.d("illScreenViewMos", student.imageLink)

    Dialog(onDismissRequest = { onDismiss(student) }) {
        Surface(modifier = Modifier
            .fillMaxWidth()
            .heightIn()
            .neu(defaultPressedNetAttrs())
            .background(MaterialTheme.colorScheme.background.copy(alpha = 0.0f)),
            shape = RoundedCornerShape(12.dp)) {
            Column(modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)) {
                val inputFieldsModifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth()
                    .background(
                        shape = RoundedCornerShape(corner = CornerSize(6.dp)),
                        color = Color.Transparent
                    )

                Spacer(modifier = Modifier.height(10.dp))

                Column(modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = "Update Email", fontSize = 18.sp,
                        color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.8f),
                        style = MaterialTheme.typography.headlineSmall)
                }

                Spacer(modifier = Modifier.height(10.dp))

                Column(modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp)) {
                    val keyboardType = KeyboardType.Text
                    InputText(text = imageLink.value,
                        label = if (imageLink.value.isEmpty()) "Image URL" else "",
                        keyboardType = keyboardType,
                        modifier = inputFieldsModifier,
                        interactionSource = interactImageLink,
                        isFieldBlank = error.value,
                        onTextChange = {
                            imageLink.value = it; error.value = false
                        })
                }

                Spacer(modifier = Modifier.height(10.dp))


                Spacer(modifier = Modifier.height(20.dp))

                Column(modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp)) {
                    val buttonModifier = Modifier.height(60.dp)
                    CustomButton1(buttonText = "Done",
                        modifier = buttonModifier) {
                        val newStudent: Student
                        if (!imageLink.value.startsWith("https://")) {
                            error.value = true
                            toast?.cancel()
                            toast.show()
                        }

                        if (imageLink.value.isNotBlank()
                            && imageLink.value.startsWith("https://")) {
                            newStudent = student.copy(imageLink = imageLink.value.trimStart().trimEnd())
                            onDismiss(newStudent)
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun UpdatePasswordDialog(student: Student, context: Context,
                        onDismiss: (Student) -> Unit) {
    val oldPassword = remember {
        mutableStateOf("")
    }
    val interactOldPassword = remember {
        MutableInteractionSource()
    }
    val isOldPasswordBlank = remember {
        mutableStateOf(false)
    }

    val newPassword = remember {
        mutableStateOf("")
    }
    val interactNewPassword = remember {
        MutableInteractionSource()
    }
    val isNewPasswordBlank = remember {
        mutableStateOf(false)
    }

    val verifyPassword = remember {
        mutableStateOf("")
    }
    val interactVerifyPassword = remember {
        MutableInteractionSource()
    }
    val isVerifyPasswordBlank = remember {
        mutableStateOf(false)
    }

    var toast: Toast?

    Dialog(onDismissRequest = { onDismiss(student) }) {
        Surface(modifier = Modifier
            .fillMaxWidth()
            .heightIn()
            .neu(defaultPressedNetAttrs())
            .background(MaterialTheme.colorScheme.background.copy(alpha = 0.0f)),
            shape = RoundedCornerShape(12.dp)) {
            Column(modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)) {
                val inputFieldsModifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth()
                    .background(
                        shape = RoundedCornerShape(corner = CornerSize(6.dp)),
                        color = Color.Transparent
                    )

                Spacer(modifier = Modifier.height(10.dp))

                Column(modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = "Update Student Name", fontSize = 18.sp,
                        color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.8f),
                        style = MaterialTheme.typography.headlineSmall)
                }

                Spacer(modifier = Modifier.height(10.dp))

                Column(modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp)) {
                    PasswordInputField(text = oldPassword.value,
                        label = if (oldPassword.value.isEmpty()) "Old Password" else "",
                        modifier = inputFieldsModifier,
                        interactionSource = interactOldPassword,
                        isFieldBlank = isOldPasswordBlank.value,
                        onTextChange = {
                            if (it.all { char ->
                                    char.isLetter() || char.isDigit()
                                }) oldPassword.value = it
                            isOldPasswordBlank.value = false
                        })
                }

                Spacer(modifier = Modifier.height(10.dp))

                Column(modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp)
                    .background(
                        shape = RoundedCornerShape(corner = CornerSize(6.dp)),
                        color = Color(0xFFEEEFF1)
                    )) {
                    PasswordInputField(text = newPassword.value,
                        label = if (newPassword.value.isEmpty()) "New Password" else "",
                        modifier = inputFieldsModifier,
                        interactionSource = interactNewPassword,
                        isFieldBlank = isNewPasswordBlank.value,
                        onTextChange = {
                            if (it.all { char ->
                                    char.isLetter() || char.isDigit()
                                }) newPassword.value = it
                            isNewPasswordBlank.value = false
                        })
                }

                Spacer(modifier = Modifier.height(10.dp))

                Column(modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp)
                    .background(
                        shape = RoundedCornerShape(corner = CornerSize(6.dp)),
                        color = Color(0xFFEEEFF1)
                    )) {
                    PasswordInputField(text = verifyPassword.value,
                        label = if (verifyPassword.value.isEmpty()) "Confirm Password" else "",
                        modifier = inputFieldsModifier,
                        interactionSource = interactVerifyPassword,
                        isFieldBlank = isVerifyPasswordBlank.value,
                        onTextChange = {
                            if (it.all { char ->
                                    char.isLetter() || char.isDigit()
                                }) verifyPassword.value = it
                            isVerifyPasswordBlank.value = false
                        })
                }

                Spacer(modifier = Modifier.height(20.dp))

                Column(modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp)) {
                    val buttonModifier = Modifier.height(60.dp)
                    CustomButton1(buttonText = "Done",
                        modifier = buttonModifier) {
                        val newStudent: Student
                        if (Uncategorized.verifyPassword(oldPassword.value, student.password) && newPassword.value.length >= 6
                            && verifyPassword.value == newPassword.value) {
                            newStudent = student.copy(password = Uncategorized.hashPassword(newPassword.value))
                            Log.d("HashedPasswords", Uncategorized.hashPassword(newPassword.value))
                            onDismiss(newStudent)
                        }
                        if (!Uncategorized.verifyPassword(oldPassword.value, student.password)) {
                            isOldPasswordBlank.value = true
                            toast = Toast.makeText(context, "Invalid old password", Toast.LENGTH_SHORT)
                            toast?.cancel()
                            toast?.show()
                        }

                        if (Uncategorized.verifyPassword(oldPassword.value, student.password) && newPassword.value.length < 6) {
                            isNewPasswordBlank.value = true
                            toast = Toast.makeText(context, "Weak Password", Toast.LENGTH_SHORT)
                            toast?.cancel()
                            toast?.show()
                        }else if (verifyPassword.value != newPassword.value) {
                            isVerifyPasswordBlank.value = true
                            toast = Toast.makeText(context, "Password didn't match", Toast.LENGTH_SHORT)
                            toast?.cancel()
                            toast?.show()
                        }
                    }
                }
            }
        }
    }
}



@Composable
fun UpdateDateDialog(
    student: MutableState<Student?>,
    context: Context,
    updateDate: MutableState<Boolean>
) {
    val currentDate = LocalDateTime.now()

    var selectedDateOfBirth: LocalDateTime?

    // Create a date picker dialog with the current date as the default
        val datePickerDialog = DatePickerDialog(
            context,
            { _, year, month, dayOfMonth ->
                // Create a LocalDateTime object from the selected date
                val selectedDate = LocalDateTime.of(year, month + 1, dayOfMonth, 0, 0)
                selectedDateOfBirth = selectedDate
                val oldBirthDate = student.value?.dateOfBirth ?: LocalDateTime.now()
                student.value = student.value?.copy(dateOfBirth = selectedDateOfBirth ?: oldBirthDate)
                updateDate.value = false

            },
            currentDate.year,
            currentDate.monthValue - 1,
            currentDate.dayOfMonth
        )

        // Show the date picker dialog
        datePickerDialog.show()
        updateDate.value = false
}