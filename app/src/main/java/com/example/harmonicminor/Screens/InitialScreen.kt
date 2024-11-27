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
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = backgroundColor),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ){
        Spacer(modifier = Modifier.height(40.dp))

        Image(
            painter = painterResource(R.drawable.login_logo),
            contentDescription = "Login image",
            modifier = Modifier.size(380.dp)
                .padding(16.dp)
        )

        Spacer(modifier = Modifier.height(80.dp))

        Text(
            text = "Bienvenido",
            fontSize = 35.sp,
            color = textColor,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Inicia sesión en",
            fontSize = 20.sp,
            color = textColor
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "HarmonicMinor",
            fontSize = 35.sp,
            color = textColor,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(100.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ){
            Box(
                modifier = Modifier
                    .background(
                        brush = Brush.linearGradient(
                            colors = listOf(buttonColor1, buttonColor2),
                        ),
                        shape = RoundedCornerShape(12.dp)
                    )
                    .clickable {
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
                modifier = Modifier
                    .background(
                        brush = Brush.linearGradient(
                            colors = listOf(buttonColor1, buttonColor2),
                        ),
                        shape = RoundedCornerShape(12.dp)
                    )
                    .clickable {
                        navController.navigate(Routes.RegisterScreen)
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

