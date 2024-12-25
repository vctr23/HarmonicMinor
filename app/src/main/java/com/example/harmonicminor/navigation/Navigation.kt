package com.example.harmonicminor.navigation

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.harmonicminor.navScreens.menu.AddressScreen
import com.example.harmonicminor.navScreens.menu.EmailUpdateScreen
import com.example.harmonicminor.navScreens.menu.FAQScreen
import com.example.harmonicminor.navScreens.menu.FeedbackScreen
import com.example.harmonicminor.navScreens.menu.Terms
import com.example.harmonicminor.screens.Initial
import com.example.harmonicminor.screens.Login
import com.example.harmonicminor.screens.PasswordResetScreen
import com.example.harmonicminor.screens.Register
import com.example.harmonicminor.screens.Splash
import com.example.harmonicminor.screens.main.Main
import com.google.firebase.auth.FirebaseAuth


@Composable
fun MyappNavigation(auth: FirebaseAuth) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Routes.SplashScreen,
        enterTransition = {
            slideInVertically(
                initialOffsetY = { it },
                animationSpec = spring(
                    stiffness = Spring.StiffnessVeryLow,
                    dampingRatio = Spring.DampingRatioNoBouncy
                )
            )
        },
        exitTransition = {
            slideOutVertically(
                targetOffsetY = { -it },
                animationSpec = spring(
                    stiffness = Spring.StiffnessVeryLow,
                    dampingRatio = Spring.DampingRatioNoBouncy
                )
            )
        },
        builder = {
            composable(Routes.InitialScreen) {
                Initial(navController, Modifier)
            }
            composable(Routes.SplashScreen) {
                Splash(navController, Modifier)
            }
            composable(Routes.RegisterScreen) {
                Register(navController, Modifier, auth)
            }
            composable(Routes.LoginScreen) {
                Login(navController, Modifier, auth)
            }
            composable(Routes.MainScreen) {
                Main(navController, Modifier, auth)
            }
            composable(Routes.TermsScreen) {
                Terms(navController)
            }
            composable(Routes.FAQScreen) {
                FAQScreen(navController)
            }
            composable(Routes.PasswordResetScreen) {
                PasswordResetScreen(auth, navController)
            }
            composable(Routes.FeedbackScreen) {
                FeedbackScreen(navController)
            }
            composable(Routes.AddressScreen){
                AddressScreen(navController)
            }
            composable(Routes.EmailUpdateScreen){
                EmailUpdateScreen(auth, navController)
            }
        }
    )
}