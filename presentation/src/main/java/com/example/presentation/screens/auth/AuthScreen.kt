package com.example.presentation.screens.auth

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.presentation.R
import com.example.presentation.components.AuthFooterText
import com.example.presentation.components.BaseButton
import com.example.presentation.components.BaseText
import com.example.presentation.components.BaseTextField
import com.example.presentation.components.SocialButton
import com.example.presentation.components.Spa
import com.example.presentation.components.SpaH
import com.example.presentation.uitils.Routs
import com.example.presentation.uitils.blue
import com.example.presentation.uitils.orangeGradient
import org.koin.androidx.compose.koinViewModel

@Composable
fun AuthScreen(navController: NavController, viewModel: AuthViewModel = koinViewModel()) {

    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val uriHandler = LocalUriHandler.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Spa(100)
        BaseText(
            text = "Вход",
            weight = FontWeight.W400,
            fontSize = 28.sp
        )
        Spa(28)
        BaseText(
            text = "Email",
        )
        Spa(8)
        BaseTextField(
            value = email.value,
            onValueChange = {
                email.value = it.filterNot { char ->
                    char in 'А'..'я' || char == 'ё' || char == 'Ё'
                }
            },
            hint = "Введите email",
            modifier = Modifier.fillMaxWidth(),
        )
        Spa(16)
        BaseText(
            text = "Пароль",
        )
        Spa(8)
        BaseTextField(
            modifier = Modifier.fillMaxWidth(),
            value = password.value,
            onValueChange = {
                password.value = it
            },
            hint = "Введите пароль"
        )
        Spa(24)
        BaseButton(
            text = "Вход",
            onClick = {
                if (viewModel.login(email.value, password.value)) {
                    navController.navigate(Routs.CoursesRout)
                }
            }
        )
        Spa(16)
        AuthFooterText()
        Spa(32)
        HorizontalDivider()
        Spa(32)
        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.Center
        ) {
            SocialButton(
                backgroundColor = blue,
                icon = painterResource(R.drawable.ic_vk)
            ){
                uriHandler.openUri("https://vk.com/")
            }
            SpaH(16)
            SocialButton(
                brush = orangeGradient,
                icon = painterResource(R.drawable.ic_ok)
            ){
                uriHandler.openUri("https://ok.ru/")
            }

        }
    }

}