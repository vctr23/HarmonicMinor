package com.example.harmonicminor.navigation

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.harmonicminor.Login
import com.example.harmonicminor.Register
import com.example.harmonicminor.Splash
import com.example.harmonicminor.Initial


@Composable
fun MyappNavigation(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Routes.SplashScreen,
        enterTransition = {slideInVertically (
            initialOffsetY = { it },
            animationSpec = spring(
                stiffness = Spring.StiffnessVeryLow,
                dampingRatio = Spring.DampingRatioNoBouncy
            )
        )},
        exitTransition = { slideOutVertically(
            targetOffsetY =  { -it },
            animationSpec = spring(
                stiffness = Spring.StiffnessVeryLow,
                dampingRatio = Spring.DampingRatioNoBouncy
            )
         )},
        builder = {
            composable(Routes.InitialScreen) {
                Initial(navController)
            }
            composable(Routes.SplashScreen) {
                Splash(navController)
            }
            composable(Routes.RegisterScreen) {
                Register(navController)
            }
            composable(Routes.LoginScreen) {
                Login(navController)
            }
        }
    )
}