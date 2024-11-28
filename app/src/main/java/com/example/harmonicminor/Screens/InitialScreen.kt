package com.example.harmonicminor.Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.harmonicminor.R
import com.example.harmonicminor.navigation.Routes
import com.example.harmonicminor.ui.theme.backgroundColor
import com.example.harmonicminor.ui.theme.buttonColor1
import com.example.harmonicminor.ui.theme.buttonColor2
import com.example.harmonicminor.ui.theme.textColor

@Composable
fun Initial(navController: NavController, modifier: Modifier){

    var clicked by rememberSaveable { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        clicked = false
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = backgroundColor),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ){
        Spacer(modifier = modifier.height(40.dp))

        Image(
            painter = painterResource(R.drawable.login_logo),
            contentDescription = "Login image",
            modifier = modifier.size(380.dp)
                .padding(16.dp)
        )

        Spacer(modifier = modifier.height(80.dp))

        Text(
            text = "Bienvenido",
            fontSize = 35.sp,
            color = textColor,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = modifier.height(20.dp))

        Text(
            text = "Inicia sesión en",
            fontSize = 20.sp,
            color = textColor
        )

        Spacer(modifier = modifier.height(20.dp))

        Text(
            text = "HarmonicMinor",
            fontSize = 35.sp,
            color = textColor,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = modifier.height(100.dp))

        Row(
            modifier = modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ){
            Box(
                modifier = modifier
                    .background(
                        brush = Brush.linearGradient(
                            colors = listOf(buttonColor1, buttonColor2),
                        ),
                        shape = RoundedCornerShape(12.dp)
                    )
                    .clickable(enabled = !clicked) {
                        clicked = true
                        navController.navigate( "LoginScreen")
                    }
                    .padding(vertical = 16.dp, horizontal = 42.dp)

            ) {
                Text(
                    text = "Iniciar sesión",
                    color = textColor
                )
            }

            Box(
                modifier = modifier
                    .background(
                        brush = Brush.linearGradient(
                            colors = listOf(buttonColor1, buttonColor2),
                        ),
                        shape = RoundedCornerShape(12.dp)
                    )
                    .clickable(enabled =!clicked) {
                        clicked = true
                        navController.navigate(Routes.RegisterScreen){
                            launchSingleTop = true
                        }
                    }
                    .padding(vertical = 16.dp, horizontal = 48.dp)
            ) {
                Text(
                    text = "Registrarse",
                    color = textColor
                )
            }
        }

    }
}

