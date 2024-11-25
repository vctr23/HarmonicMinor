package com.example.harmonicminor

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

@Composable
fun Login(navController: NavController) {

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFF121212)),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Spacer(modifier = Modifier.height(40.dp))

        Image(painter = painterResource(R.drawable.login_logo),
            contentDescription = "Login image",
            modifier = Modifier.size(380.dp)
        )

        Spacer(modifier = Modifier.height(40.dp))

        Text(text = "Bienvenido",
            fontSize = 26.sp,
            color = Color.White,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(5.dp))

        Text(text = "Inicia sesión en HarmonicMinor",
            fontSize = 20.sp, color = Color.White
        )

        Spacer(modifier = Modifier.height(40.dp))

        OutlinedTextField(value = email,
            onValueChange = {
                email = it
            }, label = {
            Text(text = "Email", color = Color.White)
        }, colors = OutlinedTextFieldDefaults.colors(
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White
            )
        )

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(value = password,
            onValueChange = {
                password = it
            }, label = {
            Text(text = "Contraseña", color = Color.White)
            },
            visualTransformation = PasswordVisualTransformation(),
            colors = OutlinedTextFieldDefaults.colors(
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White
            )
        )

        Spacer(modifier = Modifier.height(40.dp))

        Box(
            modifier = Modifier
                .background(
                    brush = Brush.linearGradient(
                        colors = listOf(Color(0xFF4776E6), Color(0xFF8E54E9)),
                    ),
                    shape = RoundedCornerShape(12.dp)
                )
                .clickable {  }
                .padding(vertical = 16.dp, horizontal = 80.dp)
        ) {
            Text(
                text = "Iniciar Sesión",
                color = Color.White
            )
        }

        Spacer(modifier = Modifier.height(30.dp))

        Text(text = "¿Olvidaste tu contraseña?",
            modifier = Modifier.clickable {  },
            color = Color.White
        )
    }
}


