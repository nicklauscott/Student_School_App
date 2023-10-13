package com.xtgem.webuild.fstcawka.page.screen

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.xtgem.webuild.fstcawka.MainActivity
import com.xtgem.webuild.fstcawka.R
import com.xtgem.webuild.fstcawka.models.enums.Screens
import com.xtgem.webuild.fstcawka.page.widget.CustomButton1
import com.xtgem.webuild.fstcawka.page.widget.InputText
import com.xtgem.webuild.fstcawka.page.widget.PasswordInputField
import com.xtgem.webuild.fstcawka.ui.theme.custom.Theme
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
    val context = LocalContext.current

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
                    .fillMaxWidth()
                    .height(55.dp)) {
                    val keyboardType = KeyboardType.Number
                    InputText(text = regNumber.value,
                        label = if (regNumber.value.isEmpty()) "Student Reg No" else "",
                        keyboardType = keyboardType,
                        modifier = inputFieldsModifier,
                        interactionSource = interactRegNo,
                        isFieldBlank = isRegNoBlank.value,
                        onTextChange = {
                            if (it.all { char ->
                                    char.isDigit() || char.isWhitespace()
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
                                    char.isLetter() || char.isWhitespace()
                                }) password.value = it
                            isPasswordBlank.value = false
                        })
                }

                Spacer(modifier = Modifier.height(20.dp))

                Column(modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp)) {
                    val buttonModifier = Modifier.height(60.dp)
                    CustomButton1(buttonText = "Login",
                        modifier = buttonModifier) {
                        Log.d("newTesterDom", "clicked")
                        if (regNumber.value.isBlank()) isRegNoBlank.value = true
                        if (password.value.isBlank()) isPasswordBlank.value = true
                        if (regNumber.value.isNotBlank() && password.value.isNotBlank()) {
                            // Login Here
//                            val intent = MainActivity.newInstance(context, Screens.Home, UUID.randomUUID())
//                            context.startActivity(intent)
                            navController.navigate(Screens.Home.withArg(regNumber.value))
                        }
                    }
                }
            }

        }
    }
}