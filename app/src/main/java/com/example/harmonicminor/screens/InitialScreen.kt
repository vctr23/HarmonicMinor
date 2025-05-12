package com.example.harmonicminor.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.harmonicminor.R
import com.example.harmonicminor.navigation.Routes
import com.example.harmonicminor.ui.theme.backgroundGradient3
import com.example.harmonicminor.ui.theme.backgroundGradient4
import com.example.harmonicminor.ui.theme.buttonColor1
import com.example.harmonicminor.ui.theme.buttonColor2
import com.example.harmonicminor.ui.theme.buttonColor3
import com.example.harmonicminor.ui.theme.buttonColor4
import com.example.harmonicminor.ui.theme.textColor

@Composable
fun Initial(navController: NavController, modifier: Modifier){
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(backgroundGradient3, backgroundGradient4)
                )
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ){
        Spacer(modifier = modifier.height(40.dp))

        Image(
            painter = painterResource(R.drawable.login_logo_2),
            contentDescription = "Login image",
            modifier = modifier.size(380.dp)
        )

        Spacer(modifier = modifier.height(40.dp))

        Text(
            text = stringResource(R.string.welcome),
            fontSize = 35.sp,
            color = textColor,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = modifier.height(20.dp))

        Text(
            text = stringResource(R.string.sing),
            fontSize = 20.sp,
            color = textColor
        )

        Spacer(modifier = modifier.height(20.dp))

        Text(
            text = stringResource(R.string.app_name),
            fontSize = 35.sp,
            color = textColor,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = modifier.height(100.dp))

        Column(
            modifier = modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Box(
                modifier = modifier
                    .background(
                        brush = Brush.linearGradient(
                            colors = listOf(buttonColor1, buttonColor2),
                        ),
                        shape = RoundedCornerShape(12.dp)
                    )
                    .clickable {
                        navController.navigate("LoginScreen") {
                            launchSingleTop = true
                        }
                    }
                    .padding(vertical = 16.dp, horizontal = 120.dp)
                    .shadow(16.dp, RoundedCornerShape(12.dp))

            ) {
                Text(
                    text = stringResource(R.string.sing_in),
                    color = textColor
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Box(
                modifier = modifier
                    .background(
                        brush = Brush.linearGradient(
                            colors = listOf(buttonColor3, buttonColor4),
                        ),
                        shape = RoundedCornerShape(12.dp)
                    )
                    .clickable {
                        navController.navigate(Routes.RegisterScreen) {
                            launchSingleTop = true
                        }
                    }
                    .padding(vertical = 16.dp, horizontal = 125.dp)
                    .shadow(16.dp, RoundedCornerShape(12.dp))
            ) {
                Text(
                    text = stringResource(R.string.sing_up),
                    color = textColor
                )
            }
        }

    }
}



