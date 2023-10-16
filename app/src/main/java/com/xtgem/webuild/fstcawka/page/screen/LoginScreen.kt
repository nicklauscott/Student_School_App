package com.xtgem.webuild.fstcawka.page.screen

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
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
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.xtgem.webuild.fstcawka.MainActivity
import com.xtgem.webuild.fstcawka.R
import com.xtgem.webuild.fstcawka.models.constants.LoginStatus
import com.xtgem.webuild.fstcawka.models.constants.State
import com.xtgem.webuild.fstcawka.models.entities.DataResult
import com.xtgem.webuild.fstcawka.models.enums.Screens
import com.xtgem.webuild.fstcawka.models.relations.CourseAndReaction
import com.xtgem.webuild.fstcawka.page.component.LoadingData
import com.xtgem.webuild.fstcawka.page.viewmodel.LoginScreenViewModel
import com.xtgem.webuild.fstcawka.page.widget.CustomButton1
import com.xtgem.webuild.fstcawka.page.widget.InputText
import com.xtgem.webuild.fstcawka.page.widget.PasswordInputField
import com.xtgem.webuild.fstcawka.ui.theme.custom.MyFonts
import com.xtgem.webuild.fstcawka.ui.theme.custom.Theme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import java.util.UUID

@Preview
@Composable
fun PreviewLoginScreen() {
    Theme {
        LoginScreen()
    }
}


@Composable
fun LoginScreen(navController: NavController = rememberNavController()) {
    val loginStatus = remember { mutableStateOf(LoginStatus(checking = false))}
    val viewModel = viewModel<LoginScreenViewModel>()
    LaunchedEffect(Unit) {
        viewModel.status.observeForever { status ->
            loginStatus.value = status
        }
    }
    val context = LocalContext.current

    val toast: Toast?
    if (loginStatus.value.status == false && loginStatus.value.reason == State.REG) {
        toast = Toast.makeText(context, "Wrong Reg No", Toast.LENGTH_SHORT)
        toast?.cancel()
        toast.show()
    }else if (loginStatus.value.status == false && loginStatus.value.reason == State.ID) {
        toast = Toast.makeText(context, "Wrong ID No", Toast.LENGTH_SHORT)
        toast?.cancel()
        toast.show()
    }else if (loginStatus.value.status == false && loginStatus.value.reason == State.Password) {
        toast = Toast.makeText(context, "Wrong Password", Toast.LENGTH_SHORT)
        toast?.cancel()
        toast.show()
    }

    val regNumber = remember {
        mutableStateOf("")
    }

    val password = remember {
        mutableStateOf("")
    }

    val interactPassword = remember {
        MutableInteractionSource()
    }
    val interactRegNo = remember {
        MutableInteractionSource()
    }

    val isRegNoBlank = remember {
        mutableStateOf<Boolean?>(null)
    }
    val isPasswordBlank = remember {
        mutableStateOf<Boolean?>(null)
    }

    val regIDLabel= remember {
        mutableStateOf("Student Reg No")
    }

    val regIdLogin = remember {
        mutableStateOf(true)
    }

    if (loginStatus.value.status == true) {
        navController.navigate(Screens.Home.withArg(loginStatus.value.userId!!))
    }

    Surface(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
    ) {
        Image(
            painter = painterResource(id = R.drawable.img),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize(),
            colorFilter = ColorFilter.tint(
                Color(0xFF6F4329),
                blendMode = BlendMode.Multiply
            )
        )
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(top = 30.dp)) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
                    .padding(top = 100.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = null, modifier = Modifier.size(150.dp),
                )
            }

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
                    .fillMaxWidth()) {
                    Row(modifier = Modifier.padding(bottom = 10.dp)) {
                       Column(modifier = Modifier
                           .clip(RoundedCornerShape(6.dp))
                           .background(
                               if (regIdLogin.value) Color(0xFFFFFFFF).copy(alpha = 0.3f)
                               else Color.Transparent
                           )
                           .padding(5.dp)
                           .clickable(enabled = !regIdLogin.value) {
                               regIdLogin.value = true
                               regIDLabel.value = "Student Reg No"
                               regNumber.value = ""
                           }) {
                           Text(text = "Reg No",
                               color =
                               if (regIdLogin.value) Color(0xFF000000).copy(alpha = 0.8f)
                               else Color(0xFFFFFFFF).copy(alpha = 0.8f),
                               style = TextStyle(
                                   fontWeight = FontWeight.SemiBold,
                                   fontSize = 18.sp, fontFamily = MyFonts.customFontFamily[3]
                               ))
                       }
                        Spacer(modifier = Modifier.width(10.dp))

                        Column(modifier = Modifier
                            .clip(RoundedCornerShape(6.dp))
                            .background(
                                if (!regIdLogin.value) Color(0xFFFFFFFF).copy(alpha = 0.3f)
                                else Color.Transparent
                            )
                            .padding(5.dp)
                            .clickable(enabled = regIdLogin.value) {
                                regIdLogin.value = false
                                regIDLabel.value = "Student ID No"
                                regNumber.value = ""
                            }) {
                            Text(text = "ID No",
                                color =
                                if (!regIdLogin.value) Color(0xFF000000).copy(alpha = 0.8f)
                                else Color(0xFFFFFFFF).copy(alpha = 0.8f),
                                style = TextStyle(
                                    fontWeight = FontWeight.SemiBold,
                                    fontSize = 18.sp, fontFamily = MyFonts.customFontFamily[3]
                                ))
                        }
                    }
                }
                Spacer(modifier = Modifier.height(3.dp))
                Column(modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp)) {
                    val keyboardType = if (regIdLogin.value) KeyboardType.Number
                        else KeyboardType.Text
                    InputText(text = regNumber.value,
                        label = if (regNumber.value.isEmpty()) regIDLabel.value else "",
                        keyboardType = keyboardType,
                        modifier = inputFieldsModifier,
                        interactionSource = interactRegNo,
                        isFieldBlank = isRegNoBlank.value,
                        onTextChange = {
                            if (it.all { char ->
                                    if (regIdLogin.value) char.isDigit()
                                    else char.isDigit() || char.isLetter()
                                }) regNumber.value = it
                            isRegNoBlank.value = false
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
                    PasswordInputField(text = password.value,
                        label = if (password.value.isEmpty()) "Password" else "",
                        modifier = inputFieldsModifier,
                        interactionSource = interactPassword,
                        isFieldBlank = isPasswordBlank.value,
                        onTextChange = {
                            if (it.all { char ->
                                    char.isLetter() || char.isDigit()
                                }) password.value = it
                            isPasswordBlank.value = false
                        })
                }

                Spacer(modifier = Modifier.height(20.dp))

                Column(modifier = Modifier
                    .fillMaxWidth()
                    .height(
                        if (loginStatus.value.checking == true)  0.dp
                        else 80.dp)
                ) {
                    val buttonModifier = Modifier.height(60.dp)
                    CustomButton1(buttonText = "Login",
                        modifier = buttonModifier) {
                        Log.d("newTesterDom", "clicked")
                        if (regNumber.value.isBlank()) isRegNoBlank.value = true
                        if (password.value.isBlank()) isPasswordBlank.value = true
                        if (regNumber.value.isNotBlank() && password.value.isNotBlank()) {
                            // Login Here
                            viewModel.verifyStudent(regLogin = regIdLogin.value,
                                regId = regNumber.value, studentId = regNumber.value,
                                textPassword = password.value)
                        }
                    }
                }

                if (loginStatus.value.checking == true) {
                    Column(modifier = Modifier
                        .fillMaxWidth()
                        .height(80.dp)) {
                        LoadingData()
                    }
                }
            }

        }
    }
}