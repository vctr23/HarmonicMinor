package com.example.harmonicminor.navigation

import android.annotation.SuppressLint
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.harmonicminor.navScreens.favourite.FavouriteScreen
import com.example.harmonicminor.navScreens.home.HomeScreen
import com.example.harmonicminor.navScreens.home.bass.BassDetailScreen
import com.example.harmonicminor.navScreens.home.bass.BassScreen
import com.example.harmonicminor.navScreens.home.dj.DjScreen
import com.example.harmonicminor.navScreens.home.drums.DrumsScreen
import com.example.harmonicminor.navScreens.home.guitar.GuitarDetailScreen
import com.example.harmonicminor.navScreens.home.guitar.GuitarScreen
import com.example.harmonicminor.navScreens.home.microphones.MicrophonesScreen
import com.example.harmonicminor.navScreens.home.piano.PianoScreen
import com.example.harmonicminor.navScreens.home.software.SoftwareScreen
import com.example.harmonicminor.navScreens.home.wind.WindScreen
import com.example.harmonicminor.navScreens.menu.MenuScreen
import com.example.harmonicminor.navScreens.menu.address.AddressScreen
import com.example.harmonicminor.navScreens.menu.emailUpdate.EmailUpdateScreen
import com.example.harmonicminor.navScreens.menu.faq.FAQScreen
import com.example.harmonicminor.navScreens.menu.feedback.FeedbackScreen
import com.example.harmonicminor.navScreens.menu.terms.Terms
import com.example.harmonicminor.navScreens.search.SearchScreen
import com.example.harmonicminor.navScreens.shopping.ShoppingCartScreen
import com.example.harmonicminor.screens.Initial
import com.example.harmonicminor.screens.Login
import com.example.harmonicminor.screens.PasswordResetScreen
import com.example.harmonicminor.screens.Register
import com.example.harmonicminor.screens.Splash
import com.example.harmonicminor.screens.main.Main
import com.google.firebase.auth.FirebaseAuth


@SuppressLint("NewApi")
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
        popEnterTransition = {
            slideInVertically(
                initialOffsetY = { it },
                animationSpec = spring(
                    stiffness = Spring.StiffnessVeryLow,
                    dampingRatio = Spring.DampingRatioNoBouncy
                )
            )
        },
        popExitTransition = {
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
                Register(navController, auth)
            }
            composable(Routes.LoginScreen) {
                Login(navController, Modifier, auth)
            }
            composable(Routes.MainScreen) {
                Main(navController, Modifier, auth)
            }
            composable(Routes.HomeScreen) {
                HomeScreen(navController)
            }
            composable(Routes.FavouriteScreen) {
                FavouriteScreen()
            }
            composable(Routes.SearchScreen) {
                SearchScreen()
            }
            composable(Routes.ShoppingCartScreen) {
                ShoppingCartScreen()
            }
            composable(Routes.MenuScreen) {
                MenuScreen(navController, auth)
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
            composable(Routes.AddressScreen) {
                AddressScreen(navController)
            }
            composable(Routes.EmailUpdateScreen) {
                EmailUpdateScreen(auth, navController)
            }
            composable(Routes.GuitarScreen) {
                GuitarScreen(navController)
            }
            composable(Routes.BassScreen){
                BassScreen(navController)
            }
            composable(Routes.PianoScreen){
                PianoScreen()
            }
            composable(Routes.DrumsScreen){
                DrumsScreen()
            }
            composable(Routes.WindScreen){
                WindScreen()
            }
            composable(Routes.DjScreen){
                DjScreen()
            }
            composable(Routes.MicrophonesScreen){
                MicrophonesScreen()
            }
            composable(Routes.SoftwareScreen){
                SoftwareScreen()
            }
            composable(
                "${Routes.GuitarDetailScreen}/{guitarName}",
                arguments = listOf(navArgument("guitarName") { type = NavType.StringType }),
                enterTransition = {
                    fadeIn(animationSpec = spring(stiffness = Spring.StiffnessLow))
                },
                exitTransition = {
                    fadeOut(animationSpec = spring(stiffness = Spring.StiffnessLow))
                },
                popEnterTransition = {
                    fadeIn(animationSpec = spring(stiffness = Spring.StiffnessLow))
                },
                popExitTransition = {
                    fadeOut(animationSpec = spring(stiffness = Spring.StiffnessLow))
                }
            ) { backStackEntry ->
                val guitarName = backStackEntry.arguments?.getString("guitarName") ?: ""
                GuitarDetailScreen(navController, guitarName)
            }
            composable(
                "${Routes.BassDetailScreen}/{bassName}",
                arguments = listOf(navArgument("bassName") { type = NavType.StringType }),
                enterTransition = {
                    fadeIn(animationSpec = spring(stiffness = Spring.StiffnessLow))
                },
                exitTransition = {
                    fadeOut(animationSpec = spring(stiffness = Spring.StiffnessLow))
                },
                popEnterTransition = {
                    fadeIn(animationSpec = spring(stiffness = Spring.StiffnessLow))
                },
                popExitTransition = {
                    fadeOut(animationSpec = spring(stiffness = Spring.StiffnessLow))
                }
            ) { backStackEntry ->
                val bassName = backStackEntry.arguments?.getString("bassName") ?: ""
                BassDetailScreen(navController, bassName)
            }
        }
    )
}