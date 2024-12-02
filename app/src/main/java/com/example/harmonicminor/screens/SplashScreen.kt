package com.example.harmonicminor.screens

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.harmonicminor.R
import com.example.harmonicminor.navigation.Routes
import com.example.harmonicminor.ui.theme.backgroundColor
import kotlinx.coroutines.delay


@Composable
fun Splash(navController: NavController, modifier: Modifier = Modifier){
    var startAnimation by remember{ mutableStateOf(false) }
    val alphaAnimation = animateFloatAsState(
        targetValue = if (startAnimation) 1f else 0f,
        animationSpec = tween(
            durationMillis = 3000
        ), label = ""
    )
    
    LaunchedEffect(key1 = true) {
        startAnimation = true
        delay(3000)
        navController.popBackStack()
        navController.navigate(Routes.InitialScreen)
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = backgroundColor),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Image(painter = painterResource(R.drawable.login_logo),
            contentDescription = "Logo",
            modifier = Modifier
                .size(450.dp)
                .padding(16.dp)
                .alpha(alphaAnimation.value)
        )
    }

}

