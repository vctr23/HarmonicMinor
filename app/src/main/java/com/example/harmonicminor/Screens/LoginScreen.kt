package com.example.harmonicminor.Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.harmonicminor.R
import com.example.harmonicminor.ui.theme.backgroundColor
import com.example.harmonicminor.ui.theme.buttonColor1
import com.example.harmonicminor.ui.theme.buttonColor2
import com.example.harmonicminor.ui.theme.textColor

@Composable
fun Login(navController: NavController, modifier: Modifier = Modifier) {

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(color = backgroundColor),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Spacer(modifier = Modifier.height(40.dp))

        Image(painter = painterResource(R.drawable.login_logo),
            contentDescription = "Login image",
            modifier = Modifier.size(380.dp)
                .padding(16.dp)
        )

        Spacer(modifier = Modifier.height(40.dp))

        Text(text = "Bienvenido",
            fontSize = 26.sp,
            color = textColor,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(5.dp))

        Text(text = "Inicia sesión en HarmonicMinor",
            fontSize = 20.sp,
            color = textColor
        )

        Spacer(modifier = Modifier.height(40.dp))

        OutlinedTextField(value = email,
            onValueChange = {
                email = it
            }, label = {
            Text(text = "Email", color = Color.White)
            },
            modifier = Modifier.width(280.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedTextColor = textColor,
                unfocusedTextColor = textColor
            ),
            singleLine = true,
            maxLines = 1
        )

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(value = password,
            onValueChange = {
                password = it
            }, label = {
            Text(text = "Contraseña", color = textColor)
            },
            modifier = Modifier.width(280.dp),
            visualTransformation = PasswordVisualTransformation(),
            colors = OutlinedTextFieldDefaults.colors(
                focusedTextColor = textColor,
                unfocusedTextColor = textColor
            ),
            singleLine = true,
            maxLines = 1
        )

        Spacer(modifier = Modifier.height(40.dp))

        Box(
            modifier = Modifier
                .background(
                    brush = Brush.linearGradient(
                        colors = listOf(buttonColor1, buttonColor2),
                    ),
                    shape = RoundedCornerShape(12.dp)
                )
                .clickable {  }
                .padding(vertical = 16.dp, horizontal = 80.dp)
        ) {
            Text(
                text = "Iniciar Sesión",
                color = textColor
            )
        }

        Spacer(modifier = Modifier.height(30.dp))

        Text(text = "¿Olvidaste tu contraseña?",
            modifier = Modifier.clickable {  },
            color = textColor
        )
    }
}


